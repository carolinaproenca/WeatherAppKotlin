package com.example.android.application.network

import com.example.android.application.models.Response
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val API: String = "bf77b23a708c515fcfd85debd24da31f"
const val URL: String = "https://api.openweathermap.org/data/2.5/"
//https://api.openweathermap.org/data/2.5/forecast?lat=41.150&lon=-8.611&appid=bf77b23a708c515fcfd85debd24da31f

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

//constructor retrofit
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(URL)
    .build()

interface WeatherAPIService {
    @GET("forecast")
    suspend fun getProperties(
        @Query("appid") key: String = API, @Query("q") city: String, @Query("units") units: String
    ): Response

}

//initialize service RETROFIT
object WeatherApi {
    val retrofitService: WeatherAPIService by lazy {
        retrofit.create(WeatherAPIService::class.java)
    }
}
