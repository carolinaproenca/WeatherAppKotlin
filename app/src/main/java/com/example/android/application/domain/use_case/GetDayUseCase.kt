package com.example.android.application.domain.use_case

import android.annotation.SuppressLint
import com.example.android.application.data.remote.dto.Hour
import com.example.android.application.domain.model.Day
import com.github.mikephil.charting.data.Entry
import java.text.SimpleDateFormat
import java.util.*

class GetDayUseCase(){

    private val days = mutableListOf<Day>()
    private val arrayhour = arrayListOf<Hour>()
    private val linelist = ArrayList<Entry>()
    private var firstday = false

    fun getDay(hour: List<Hour>): List<Day> {

        for(i in hour.indices) {
            when {
                i == 0 -> { //add first hour to array
                    arrayhour.add(hour[i])
                    firstday = true
                }
                getData(hour[i - 1].dtTxt) != getData(hour[i].dtTxt) -> { // create a new day because the hour is different and is not the same day
                    firstday = false
                    days.add(
                        Day(
                            arrayhour.clone() as List<Hour>,
                            day(hour[i - 1].dtTxt)
                        )
                    )
                    arrayhour.clear()
                    arrayhour.add(hour[i])
                }
                else -> { //date equals
                    arrayhour.add(hour[i])
                }
            }
            if (firstday) {
                linelist.add(
                    Entry(
                        getData(hour[i].dtTxt)!!.toFloat(),
                        hour[i].main.temp.toFloat()
                    )
                )
            }
        }
        return days
    }

    @SuppressLint("SimpleDateFormat")
    fun getData(stringdate: String): Int? {
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm")
        val date = format.parse(stringdate)
        return date?.date
    }

    @SuppressLint("SimpleDateFormat")
    fun day(stringdate: String): Date {
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm")
        return format.parse(stringdate)!!
    }

    fun getEntries(): ArrayList<Entry> {
        return linelist
    }

}

