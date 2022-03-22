package com.example.android.application.models
import com.squareup.moshi.Json


data class Sys(
    @Json(name = "pod")
    val pod: String
)