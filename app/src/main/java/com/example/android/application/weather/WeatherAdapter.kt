package com.example.android.application.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android.application.databinding.RvhoursBinding
import com.example.android.application.models.Day
import com.example.android.application.models.Hour

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.MyviewHolder>() {

    var data = ArrayList<Day>()

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MyviewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : MyviewHolder{
        return MyviewHolder.from(parent)
    }

    fun setDate(newdata : List<Day>){
        val diffCallback = DiffCallback(data, newdata)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        data.clear()
        data.addAll(newdata)
        diffResult.dispatchUpdatesTo(this)
    }

    class MyviewHolder private constructor(private val binding : RvhoursBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item : Day){
            binding.days.text = item.date.toString()
            binding.rvhours.adapter = HoursAdapter(item.hours as ArrayList<Hour>)
           // binding.rvhours.adapter.sethour(item.hours as ArrayList<Hour>)
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


class DiffCallback(private val listDays: List<Day>, private val newListDays: List<Day>) : DiffUtil.Callback(){
    override fun getOldListSize(): Int = listDays.size
    override fun getNewListSize(): Int = newListDays.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return listDays[oldItemPosition].date == newListDays[newItemPosition].date
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val list = listDays[oldItemPosition].date
        val newlist = newListDays[newItemPosition].date
        return list == newlist
    }

    @Nullable
    override fun getChangePayload(oldPosition: Int, newPosition: Int): Any? {
        return super.getChangePayload(oldPosition, newPosition)
    }
}

