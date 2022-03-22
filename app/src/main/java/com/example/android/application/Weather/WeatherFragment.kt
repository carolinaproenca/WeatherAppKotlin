package com.example.android.application.Weather

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.android.application.databinding.WeatherFragmentBinding
import org.json.JSONObject

class WeatherFragment : Fragment(){

    private lateinit var binding: WeatherFragmentBinding
    private lateinit var model : WeatherViewModel
    private lateinit var rv : RecyclerView
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
            Log.d("Ola", "Ola" +error)
        }
        model.response.observe(this){ item ->
            binding.grafico.text = item.city.name
            Log.d("Ola", "Ola" +item.city)
            adapter.data = item.list
        }
    }

    private fun setValues(response : JSONObject){
        var deg = response.getJSONObject("main").getString("temp")

    }

}
