package com.example.android.application.data.remote.dto
import com.squareup.moshi.Json


data class Sys(
    @Json(name = "pod")
    val pod: String
)