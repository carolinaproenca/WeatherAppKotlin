package com.example.android.application.weather

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android.application.databinding.WeatherFragmentBinding

class WeatherFragment : Fragment(){

    private lateinit var binding: WeatherFragmentBinding
    private lateinit var model : WeatherViewModel
    private val adapter by lazy{ WeatherAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = WeatherFragmentBinding.inflate(inflater, container, false)

        model = ViewModelProvider(this).get(WeatherViewModel::class.java)

        binding.day1Time.adapter = adapter


        model.getWeatherProperties()

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        model.smsError.observe(this){ error ->
            binding.grafico.text = error
            Log.d("Ola", "Ola$error")
        }
        model.response.observe(this){ item ->
            binding.city.text = item.city.name
            binding.currentWeather.text = item.list[0].main.temp.toString()
            Log.d("Ola", "Ola" +item.city)
            adapter.data = item.list
        }
    }

}
