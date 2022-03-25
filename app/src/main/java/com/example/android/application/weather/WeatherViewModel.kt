package com.example.android.application.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.application.models.Day
import com.example.android.application.models.Hour
import com.example.android.application.models.Response
import com.example.android.application.models.WeatherVM
import com.example.android.application.network.WeatherApi

import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class


WeatherViewModel : ViewModel(){

    private val retrofit = WeatherApi.retrofitService

    private val _response = MutableLiveData<WeatherVM>()
    val response: LiveData<WeatherVM>
        get() = _response

    val smsError = MutableLiveData<String>()

    //call web service
    fun getWeatherProperties(){
        viewModelScope.launch {

            try {
                val listResult = retrofit.getProperties(city = "Porto", units = "metric")

                val days = mutableListOf<Day>()
                val arrayhour = arrayListOf<Hour>()

                for(i in listResult.list.indices){

                    if(i == 0){ // adicionar a primeira hora ao array auxiliar de horas
                        arrayhour.add(listResult.list[i])
                    }else if(getData(listResult.list[i-1].dtTxt) != getData(listResult.list[i].dtTxt)){ // criar um novo dia pq a hora ja nao é do mesmo dia
                        days.add(Day(arrayhour.clone() as List<Hour>,day(listResult.list[i-1].dtTxt)))
                        arrayhour.clear()
                        arrayhour.add(arrayhour[i])
                    }else{ //datas sao iguais
                        arrayhour.add(listResult.list[i])
                    }
                }
                _response.value = WeatherVM(listResult.city.name, listResult.list[0].main.temp.toString(),days)

            } catch (e: Exception) {
                smsError.value = "Failure+$e"
            }
        }
    }

    fun getData(stringdate: String): Int { // ver aqui qual é o dia em que estamos
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm")
        val date = format.parse(stringdate)
        return date.date
    }

    fun day(stringdate: String): Date{
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm")
        val day = format.parse(stringdate)
        return day
    }

}
