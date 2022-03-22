package com.example.android.application.models
import com.squareup.moshi.Json


data class Rain(
    @Json(name = "3h")
    val h: Double
)