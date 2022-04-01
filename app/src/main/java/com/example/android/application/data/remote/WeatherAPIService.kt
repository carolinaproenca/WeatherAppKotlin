package com.example.android.application.data.remote

import com.example.android.application.common.Constants.URL
import com.example.android.application.common.Constants.API
import com.example.android.application.data.remote.dto.Response
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

//constructor retrofit
val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(URL)
    .build()!!

interface WeatherAPIService {
    @GET("forecast")
    suspend fun getProperties(
        @Query("appid") key: String = API, @Query("q") city: String, @Query("units") units: String
    ): Response

}
