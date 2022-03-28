package com.example.android.application.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.application.databinding.ViewDegreesHoursBinding
import com.example.android.application.models.Day
import com.example.android.application.models.Hour

class HoursAdapter : ListAdapter<Hour, RecyclerView.ViewHolder>(DiffCallbackAdapter()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        (holder as ViewHolder).bind(item)
    }

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

class DiffCallbackAdapter : DiffUtil.ItemCallback<Hour>(){

    override fun areContentsTheSame(oldItem: Hour, newItem: Hour): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: Hour, newItem: Hour): Boolean {
        return oldItem.dtTxt == newItem.dtTxt
    }

}