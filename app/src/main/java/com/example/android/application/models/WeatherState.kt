package com.example.android.application.models

import com.github.mikephil.charting.data.Entry

data class WeatherState(
    val name: String,
    val current: String,
    val entries: ArrayList<Entry>,
    val days: List<Day>)