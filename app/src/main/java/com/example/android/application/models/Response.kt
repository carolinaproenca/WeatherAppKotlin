package com.example.android.application.models
import com.squareup.moshi.Json

data class Response(

    @Json(name = "city")
    val city: City,
    @Json(name = "cnt")
    val cnt: Int,
    @Json(name = "cod")
    val cod: String,
    @Json(name = "list")
    val list: List<Hour>,
    @Json(name = "message")
    val message: Int

)