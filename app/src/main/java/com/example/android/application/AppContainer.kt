package com.example.android.application

import com.example.android.application.common.WeatherApi.retrofitService
import com.example.android.application.data.repository.WeatherRepository

class AppContainer {

    val repository = WeatherRepository(retrofitService)

    var weatherContainer : WeatherContainer ?=null

}