package com.example.android.application.weather

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.application.databinding.ViewDegreesHoursBinding
import com.example.android.application.models.Hour

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.MyviewHolder>() {

    var data = listOf<Hour>()

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
        return MyviewHolder(ViewDegreesHoursBinding.inflate(LayoutInflater.from(parent.context)))
    }

    class MyviewHolder internal constructor(private val binding: ViewDegreesHoursBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: Hour){
            binding.hour.text = item.dtTxt
            binding.degrees.text = item.main.temp.toString()
        }
    }
}

