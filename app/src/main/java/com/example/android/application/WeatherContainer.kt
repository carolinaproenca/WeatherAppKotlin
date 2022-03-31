package com.example.android.application

import com.example.android.application.data.repository.WeatherRepository
import com.example.android.application.domain.use_case.GetDayUseCase
import com.example.android.application.presentation.fragment.WeatherViewModelFactory

class WeatherContainer(weatherRepository: WeatherRepository) {

    //private val dayUseCase = GetDayUseCase(weatherRepository)

    val weatherViewModelFactory = WeatherViewModelFactory(weatherRepository)
}