package com.example.android.application.weather

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.application.models.Day
import com.example.android.application.models.Hour
import com.example.android.application.models.WeatherVM
import com.example.android.application.network.WeatherApi

import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

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
                val arrayhour = arrayListOf<Hour>()


                for(i in listResult.list.indices){
                    when {
                        i == 0 -> { //add first hour to array
                            arrayhour.add(listResult.list[i])
                        }
                        getData(listResult.list[i - 1].dtTxt) != getData(listResult.list[i].dtTxt) -> { // create a new day because the hour is different and is not the same day
                            days.add(
                                Day(
                                    arrayhour.clone() as List<Hour>,
                                    day(listResult.list[i - 1].dtTxt)
                                )
                            )
                            arrayhour.clear()
                            arrayhour.add(listResult.list[i])
                        }
                        else -> { //date equals
                            arrayhour.add(listResult.list[i])
                        }
                    }
                }
                _response.value = WeatherVM(listResult.city.name, listResult.list[0].main.temp.toString(),days)
            } catch (e: Exception) {
                smsError.value = "Failure+$e"
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun getData(stringdate: String): Int {
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm")
        val date = format.parse(stringdate)
        return date.date
    }

    @SuppressLint("SimpleDateFormat")
    private fun day(stringdate: String): Date {
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm")
        return format.parse(stringdate)
    }

    suspend fun getDegree(indice: Int): Double {
        val listResult = retrofit.getProperties(city = "Porto", units = "metric")
        return listResult.list[indice].main.temp
    }

    suspend fun getHour(indice: Int): String {
        val listResult = retrofit.getProperties(city = "Porto", units = "metric")
        return listResult.list[indice].dtTxt
    }

}
