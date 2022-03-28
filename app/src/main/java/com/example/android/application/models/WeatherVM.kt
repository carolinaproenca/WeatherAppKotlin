package com.example.android.application.models

import com.github.mikephil.charting.data.Entry


// TODO rename to Weather state
data class WeatherVM(
    val name: String,
    val current: String,
    val entries:List<Entry>,
    val days: List<Day>)
//  TODO Add list of entries