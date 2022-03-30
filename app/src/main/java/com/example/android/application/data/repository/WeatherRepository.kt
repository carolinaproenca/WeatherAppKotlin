package com.example.android.application.data.repository

import com.example.android.application.data.remote.WeatherAPIService
import com.example.android.application.data.remote.dto.Response

class WeatherRepository constructor(private val weatherAPIService: WeatherAPIService){

    suspend fun getProp(): Response {
        return weatherAPIService.getProperties(city = "Porto", units = "metric")
    }

}