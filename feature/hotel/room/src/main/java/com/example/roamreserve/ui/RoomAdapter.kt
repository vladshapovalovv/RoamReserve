package com.example.roamreserve.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roamreserve.domain.entity.Room
import com.example.roamreserve.room.databinding.RoomItemBinding

class RoomAdapter(
    private val roomSelected: () -> Unit
) : RecyclerView.Adapter<RoomViewHolder>() {

    var items: List<Room> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val itemBinding = RoomItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RoomViewHolder(itemBinding, roomSelected)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
}