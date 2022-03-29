package com.example.android.application.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.application.databinding.RvhoursBinding
import com.example.android.application.models.Day

class WeatherAdapter : ListAdapter<Day ,RecyclerView.ViewHolder>(DiffCallback()) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        (holder as MyviewHolder).bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : MyviewHolder{
        return MyviewHolder.from(parent)
    }


    class MyviewHolder private constructor(private val binding : RvhoursBinding) : RecyclerView.ViewHolder(binding.root){
        private val adapter3 = HoursAdapter()
        fun bind(item : Day){
            adapter3.submitList(item.hours)
            binding.days.text = item.date.toString()
            binding.rvhours.adapter = adapter3
        }
        companion object{
            fun from(parent: ViewGroup) : MyviewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RvhoursBinding.inflate(layoutInflater, parent, false)
                return MyviewHolder(binding)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Day>(){
        override fun areItemsTheSame(oldItem: Day, newItem: Day): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Day, newItem: Day): Boolean {
            return oldItem.date == newItem.date
        }
    }
}




