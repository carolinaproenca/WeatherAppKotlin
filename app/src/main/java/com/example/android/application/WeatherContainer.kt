package com.example.android.application

import com.example.android.application.data.repository.WeatherRepository
import com.example.android.application.domain.use_case.GetDayUseCase

class WeatherContainer(weatherRepository: WeatherRepository) {

    val dayUseCase = GetDayUseCase(weatherRepository)

}