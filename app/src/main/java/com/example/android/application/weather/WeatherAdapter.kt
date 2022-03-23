package com.example.android.application.weather

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.application.databinding.ViewDegreesHoursBinding
import com.example.android.application.models.Day
import com.example.android.application.models.Hour

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.MyviewHolder>() {

    var data = listOf<Day>()

    @SuppressLint("NotifyDataSetChanged")
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MyviewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyviewHolder {
        return MyviewHolder.from(parent)
    }

    class MyviewHolder private constructor(private val binding: ViewDegreesHoursBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: Day){
            binding.hour.text = item.dtTxt
            binding.degrees.text = item.main.temp.toString()
        }

        companion object{
            fun from(parent: ViewGroup): MyviewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ViewDegreesHoursBinding.inflate(layoutInflater,parent,false)
                return MyviewHolder(binding)
            }
        }
    }
}

