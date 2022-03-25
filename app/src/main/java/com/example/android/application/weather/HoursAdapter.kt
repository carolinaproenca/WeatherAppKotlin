package com.example.android.application.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android.application.databinding.ViewDegreesHoursBinding
import com.example.android.application.models.Hour

class HoursAdapter(private var listhour : ArrayList<Hour>) :RecyclerView.Adapter<HoursAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listhour[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = listhour.size

    fun sethour(newHour : List<Hour>){
        val diffCallback = DiffCallbackAdapter(listhour, newHour)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        listhour.clear()
        listhour.addAll(newHour)
        diffResult.dispatchUpdatesTo(this)
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

class DiffCallbackAdapter(private val listHours: List<Hour>, private val newListHours: List<Hour>) : DiffUtil.Callback(){
    override fun getOldListSize(): Int = listHours.size
    override fun getNewListSize(): Int = newListHours.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return listHours[oldItemPosition].dtTxt == newListHours[newItemPosition].dtTxt
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val list = listHours[oldItemPosition].dtTxt
        val newlist = newListHours[newItemPosition].dtTxt
        return list == newlist
    }

    @Nullable
    override fun getChangePayload(oldPosition: Int, newPosition: Int): Any? {
        return super.getChangePayload(oldPosition, newPosition)
    }
}