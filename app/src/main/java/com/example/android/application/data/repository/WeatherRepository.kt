package com.example.android.application.data.repository

import com.example.android.application.data.remote.WeatherAPIService
import com.example.android.application.data.remote.dto.Response
import com.example.android.application.domain.repository.DomainRepository

class WeatherRepository constructor(private val weatherAPIService: WeatherAPIService) : DomainRepository{

    override suspend fun getProp(): Response {
        return weatherAPIService.getProperties(city = "Porto", units = "metric")
    }

}