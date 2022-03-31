package com.example.android.application

import com.example.android.application.data.remote.WeatherAPIService
import com.example.android.application.data.remote.retrofit
import com.example.android.application.data.repository.WeatherRepository

class AppContainer {

    private val retrofitService: WeatherAPIService by lazy {
        retrofit.create(WeatherAPIService::class.java)
    }
    val repository = WeatherRepository(retrofitService)

    val weatherViewModelFactory = WeatherRepository(retrofitService)

    var weatherContainer : WeatherContainer ?=null

}