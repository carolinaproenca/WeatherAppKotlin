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

                var days = mutableListOf<Day>()
                val arrayhour = arrayListOf<Hour>()

                //days = listResult.list[0]
                arrayhour[0] = listResult.list[0]

                for(i in 0..arrayhour.size){

                    val day = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                    val parseday = LocalDateTime.parse(listResult.list[i].dtTxt, day)

                    if(getData() == parseday) // se o dia em que temos guardado (getData()) é igual ao dia que recebemos
                        arrayhour.add(arrayhour[i]) // adicionamos ao array das horas a hora
                    else// senão vamos criar um novo dia ao array de dias com a hora que temos e com o dia que recebemos
                        days.add(Day(arrayhour,parseday))

                }

                _response.value = WeatherVM(listResult.city.name, listResult.list[0].main.temp.toString(),days)

            } catch (e: Exception) {
                smsError.value = "Failure+$e"
            }
        }
    }

    fun getData(){ // ver aqui qual é o dia em que estamos
        val day = Day()
        val currentDay
        day.date = currentDay
        return currentDay
    }
}
