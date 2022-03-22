package com.example.android.application.models
import com.squareup.moshi.Json

data class Clouds(
    @Json(name = "all")
    val all: Int
)