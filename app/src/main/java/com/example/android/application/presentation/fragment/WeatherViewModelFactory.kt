package com.example.android.application.presentation.fragment

import com.example.android.application.data.repository.WeatherRepository

class WeatherViewModelFactory(private val weatherRepository: WeatherRepository): Factory<WeatherViewModel> {
    override fun create():WeatherViewModel{
        return WeatherViewModel()
    }
}