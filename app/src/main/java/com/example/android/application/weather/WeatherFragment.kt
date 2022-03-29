package com.example.android.application.weather

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android.application.R
import com.example.android.application.databinding.WeatherFragmentBinding
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

class WeatherFragment : Fragment(){

    private lateinit var binding: WeatherFragmentBinding
    private lateinit var model : WeatherViewModel
    private val adapter by lazy{ WeatherAdapter() }

    private var linelist = ArrayList<Entry>()
    private lateinit var lineDataSet : LineDataSet
    private lateinit var lineData : LineData

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = WeatherFragmentBinding.inflate(inflater, container, false)

        model = ViewModelProvider(this).get(WeatherViewModel::class.java)

        binding.day1weather.adapter = adapter


        model.getWeatherProperties()

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        model.smsError.observe(this){ error ->
            Log.d("Ola", "Ola$error")
        }
        model.response.observe(this){ item ->
            binding.city.text = item.name
            binding.currentWeather.text = item.current
            Log.d("Ola", "Ola" +item.name)
            adapter.submitList(item.days)
            //adapter.data = item.days as ArrayList<Day>

           /* for(i in item.entryhour.indices){
                linelist.add(Entry(item.entryhour[i].toFloat(), item.entrydegrees[i].toFloat()))
            }*/

            linelist = item.entries
           /* linelist.add(Entry(10f, 20f))
            linelist.add(Entry(20f, 30f))
            linelist.add(Entry(40f, 50f))
            linelist.add(Entry(60f, 70f))
            linelist.add(Entry(80f, 90f))*/
            lineDataSet = LineDataSet(linelist, "Weather")

            lineDataSet.color = R.color.purple_200
            lineDataSet.circleRadius = 5f

            binding.lineChart.data = LineData(lineDataSet)

            //connect to UI
            lineData = LineData(lineDataSet)
            binding.lineChart.data = lineData
        }
    }

}

