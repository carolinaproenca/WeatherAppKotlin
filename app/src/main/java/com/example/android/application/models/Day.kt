package com.example.android.application.models

import com.squareup.moshi.Json
import java.time.LocalDate
import java.util.*

data class Day(var hours: List<Hour>, var date: LocalDate) {
}