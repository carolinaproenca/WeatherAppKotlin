package com.example.android.application.weather

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.application.R
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
        //holder.bind(item)
        holder.hour.text = item.dtTxt
        holder.degrees.text = item.dtTxt
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyviewHolder {
        return MyviewHolder.from(parent)
    }

    class MyviewHolder private constructor(itemView: View): RecyclerView.ViewHolder(itemView){
        val hour: TextView = itemView.findViewById(R.id.hour)
        val degrees: TextView = itemView.findViewById(R.id.degrees)



        companion object {
            fun from(parent: ViewGroup): MyviewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.view_degrees_hours, parent, false)

                return MyviewHolder(view)
            }
        }
    }

/*  class MyviewHolder private constructor(val binding: WeatherFragmentBinding) : RecyclerView.ViewHolder(binding.root){
         fun bind(item: Day){
             binding.day1Time = item.hours
         }
         companion object {
             fun from(parent: ViewGroup): MyviewHolder {
                 val layoutInflater = LayoutInflater.from(parent.context)
                 val view = layoutInflater
                     .inflate(R.layout.view_degrees_hours, parent, false)

                 return MyviewHolder(view)
             }
         }
    }*/



}

