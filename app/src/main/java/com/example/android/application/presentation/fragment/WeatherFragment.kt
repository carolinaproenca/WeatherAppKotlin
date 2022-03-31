package com.example.android.application.presentation.fragment

//import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android.application.AppContainer
import com.example.android.application.R
import com.example.android.application.WeatherApplication
import com.example.android.application.WeatherContainer
import com.example.android.application.databinding.WeatherFragmentBinding
import com.example.android.application.presentation.adapters.WeatherAdapter
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet


abstract class WeatherFragment : Fragment(){

    private lateinit var binding: WeatherFragmentBinding
    private lateinit var model : WeatherViewModel
    private val adapter by lazy{ WeatherAdapter() }

    private var linelist = ArrayList<Entry>()
    private lateinit var lineDataSet : LineDataSet
    private lateinit var lineData : LineData

    //abstract val application : Application
    private lateinit var appContainer: AppContainer

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = WeatherFragmentBinding.inflate(inflater, container, false)

        appContainer = (application as WeatherApplication).appContainer
        appContainer.weatherContainer = WeatherContainer(appContainer.repository)

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

            linelist = item.entries

            lineDataSet = LineDataSet(linelist, "Weather")

            lineDataSet.color = R.color.purple_200
            lineDataSet.circleRadius = 5f

            binding.lineChart.data = LineData(lineDataSet)

            //connect to UI
            lineData = LineData(lineDataSet)
            binding.lineChart.data = lineData
        }
    }

    override fun onDestroy() {
        appContainer.weatherContainer = null
        super.onDestroy()
    }

}

