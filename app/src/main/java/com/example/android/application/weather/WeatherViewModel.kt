package com.example.android.application.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.application.models.Response
import com.example.android.application.network.WeatherApi

import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel(){

    private val retrofit = WeatherApi.retrofitService

    private val _response = MutableLiveData<Response>()
    val response: LiveData<Response>
        get() = _response

    val smsError = MutableLiveData<String>()

    //call web service

    fun getWeatherProperties(){
        viewModelScope.launch {

            try {
                val listResult = retrofit.getProperties(city = "Porto", units = "metric")

                _response.value = listResult

            } catch (e: Exception) {
                smsError.value = "Failure+$e"
            }
        }
    }

}