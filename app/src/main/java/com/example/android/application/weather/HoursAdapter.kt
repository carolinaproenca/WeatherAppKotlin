package com.example.android.application.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.application.databinding.ViewDegreesHoursBinding
import com.example.android.application.models.Hour

class HoursAdapter(private var listhour : List<Hour>) :RecyclerView.Adapter<HoursAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listhour[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = listhour.size

    class ViewHolder private constructor(private val binding : ViewDegreesHoursBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item : Hour){
            binding.hour.text = item.dtTxt
            binding.degrees.text = item.main.temp.toString()
        }
        companion object{
            fun from(parent: ViewGroup) : ViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ViewDegreesHoursBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}