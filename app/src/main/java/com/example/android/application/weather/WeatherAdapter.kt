package com.example.android.application.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.application.databinding.RvhoursBinding
import com.example.android.application.models.Day

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.MyviewHolder>() {

    var data = listOf<Day>()

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MyviewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : MyviewHolder{
        return MyviewHolder.from(parent)
    }

    class MyviewHolder private constructor(private val binding : RvhoursBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item : Day){
            binding.days.text = item.date.toString()
            binding.rvhours.adapter = HoursAdapter(item.hours)
        }
        companion object{
            fun from(parent: ViewGroup) : MyviewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RvhoursBinding.inflate(layoutInflater, parent, false)
                return MyviewHolder(binding)
            }
        }
    }
}

