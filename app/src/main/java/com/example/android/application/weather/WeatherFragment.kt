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
import com.example.android.application.models.Day
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.android.synthetic.main.weather_fragment.*

class WeatherFragment : Fragment(){

    private lateinit var binding: WeatherFragmentBinding
    private lateinit var model : WeatherViewModel
    private val adapter by lazy{ WeatherAdapter() }

    private val date = ArrayList<Day>()
    private lateinit var linelist : ArrayList<Entry>
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
        adapter.setDate(date)

        model.getWeatherProperties()

        configureLineChart()

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
            adapter.data = item.days as ArrayList<Day>

        }
    }

     fun configureLineChart(){

        //Entry(first hours and second degrees)
        /*linelist.add((Entry(model.getHour(0).toFloat(), model.getDegree(0).toFloat())))
        linelist.add((Entry(model.getHour(1).toFloat(),model.getDegree(1).toFloat())))
        linelist.add((Entry(model.getHour(2).toFloat(),model.getDegree(2).toFloat())))
        linelist.add((Entry(model.getHour(3).toFloat(),model.getDegree(3).toFloat())))
        linelist.add((Entry(model.getHour(4).toFloat(),model.getDegree(4).toFloat())))
        linelist.add((Entry(model.getHour(5).toFloat(),model.getDegree(5).toFloat())))
        linelist.add((Entry(model.getHour(6).toFloat(),model.getDegree(6).toFloat())))
        linelist.add((Entry(model.getHour(7).toFloat(),model.getDegree(7).toFloat())))*/
        linelist.add(Entry(20f, 0.0F))
        linelist.add(Entry(30f, 3.0F))
        linelist.add(Entry(40f, 2.0F))
        linelist.add(Entry(50f, 1.0F))
        linelist.add(Entry(60f, 8.0F))
        linelist.add(Entry(70f, 10.0F))
        linelist.add(Entry(80f, 1.0F))

      //list of properties and name
        lineDataSet = LineDataSet(linelist, "Test")

        lineDataSet.color = R.color.purple_200
        lineDataSet.circleRadius = 5f

         lineChart.data = LineData(lineDataSet)

         lineChart.setTouchEnabled(true)
         lineChart.setPinchZoom(true)

        //connect to UI
         lineData = LineData(lineDataSet)
         binding.lineChart.data = lineData
    }
}
