package com.example.android.application.data.remote.dto
import com.squareup.moshi.Json

data class Wind(
    @Json(name = "deg")
    val deg: Int,
    @Json(name = "gust")
    val gust: Double,
    @Json(name = "speed")
    val speed: Double
)