package com.example.roamreserve.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.roamreserve.room.databinding.RoomImageItemBinding

class RoomViewPagerAdapter(
    private val images: List<String>,
) :
    RecyclerView.Adapter<RoomViewPagerAdapter.PagerViewHolder>() {

    inner class PagerViewHolder(private val binding: RoomImageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(imageUrl: String) = binding.imageView.load(imageUrl)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val binding =
            RoomImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        val imageUrl = images[position]
        holder.bind(imageUrl)

    }

    override fun getItemCount() = images.size
}