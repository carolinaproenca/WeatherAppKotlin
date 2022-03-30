package com.example.android.application.domain.model

import com.example.android.application.data.remote.dto.Hour
import java.util.*

data class Day(var hours: List<Hour>, var date: Date)