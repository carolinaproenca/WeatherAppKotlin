package com.example.android.application.Weather

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.application.models.Day
import com.example.android.application.models.Json
import com.example.android.application.network.WeatherApi

import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class WeatherViewModel : ViewModel(){

    private val retrofit = WeatherApi.retrofitService

    private val _response = MutableLiveData<Json>()
    val response: LiveData<Json>
        get() = _response

    val smsError = MutableLiveData<String>()

    //call web service

    fun getWeatherProperties(){
        viewModelScope.launch {

            try {
                val listResult = retrofit.getPropertires(city = "Porto", units = "metric")

                /*for (item in listResult.list) {

                    val h = DateTimeFormatter.ISO_DATE
                    val hour = LocalDate.parse(item.dtTxt, h)

                    val days = mutableListOf<Day>()
                    days[0] = Day(listResult.list, hour)

                    for (i in 0..days.size) {
                        if (days[i] == days[i - 1])
                            days.add(i, Day(listResult.list, hour))
                        else
                            Day(listResult.list, hour)
                    }
                }*/
                _response.value = listResult

            } catch (e: Exception) {
                smsError.value = "Failure+$e"
            }
        }
    }

   /* private fun setValues(response : JSONObject){
        var deg = response.getJSONObject("main").getString("temp")
      //  degrees.text = ""
    }*/

}