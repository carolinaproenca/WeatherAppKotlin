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

class WeatherViewModel : ViewModel(){

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
                val arrayhour = arrayOf<Hour>()

            /*    arrayhour[0] = listResult.list

                for(i in 0..arrayhour.size){

                    val day : DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                    val parseday : LocalDateTime = LocalDateTime.parse(listResult.list[i].dtTxt, day)

                    if(arrayhour[i-1] != arrayhour[i])
                        Day(arrayhour[i],parseday)

                }*/

                _response.value = WeatherVM(listResult.city.name, listResult.list[0].main.temp.toString(),days)

            } catch (e: Exception) {
                smsError.value = "Failure+$e"
            }
        }
    }
}
