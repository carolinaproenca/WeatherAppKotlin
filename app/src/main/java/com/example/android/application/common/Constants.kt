package com.example.android.application.common

import com.example.android.application.data.remote.WeatherAPIService
import com.example.android.application.data.remote.retrofit

object Constants {
    const val API: String = "bf77b23a708c515fcfd85debd24da31f"
    const val URL: String = "https://api.openweathermap.org/data/2.5/"
    //https://api.openweathermap.org/data/2.5/forecast?lat=41.150&lon=-8.611&appid=bf77b23a708c515fcfd85debd24da31f
}

//initialize service RETROFIT
object WeatherApi {
    val retrofitService: WeatherAPIService by lazy {
        retrofit.create(WeatherAPIService::class.java)
    }
}
