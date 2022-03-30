package com.example.android.application.presentation.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.application.domain.model.Day
import com.example.android.application.data.remote.dto.WeatherState
import com.example.android.application.data.remote.WeatherApi
import com.example.android.application.domain.use_case.GetDayUseCase
import com.github.mikephil.charting.data.Entry

import kotlinx.coroutines.launch
import kotlin.collections.ArrayList

class WeatherViewModel : ViewModel(){

    private val retrofit = WeatherApi.retrofitService

    private val _response= MutableLiveData<WeatherState>()
    val response: LiveData<WeatherState>
        get() = _response

    val smsError = MutableLiveData<String>()

    private var day = GetDayUseCase()
    private var linelist = ArrayList<Entry>()
    private var days = mutableListOf<Day>()

    //call web service
    @Suppress("UNCHECKED_CAST")
    fun getWeatherProperties(){
        viewModelScope.launch {
            try {
                val listResult = retrofit.getProperties(city = "Porto", units = "metric")
                linelist = day.getEntries()
                days = day.getDay(listResult.list) as MutableList<Day>
                _response.value = WeatherState(listResult.city.name, listResult.list[0].main.temp.toString(),linelist,days)
            } catch (e: Exception) {
                smsError.value = "Failure+$e"
            }
        }
    }


}
