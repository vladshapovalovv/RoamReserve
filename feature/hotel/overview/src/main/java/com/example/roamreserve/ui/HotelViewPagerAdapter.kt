package com.example.roamreserve.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.roamreserve.overview.databinding.ImageItemBinding

class HotelViewPagerAdapter(
    private val images: List<String>,
) :
    RecyclerView.Adapter<HotelViewPagerAdapter.PagerViewHolder>() {

    inner class PagerViewHolder(private val binding: ImageItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(imageUrl: String) = binding.imageView.load(imageUrl)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val binding = ImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        val imageUrl = images[position]
        holder.bind(imageUrl)

    }

    override fun getItemCount() = images.size
}