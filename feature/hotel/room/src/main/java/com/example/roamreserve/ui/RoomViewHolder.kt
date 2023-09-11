package com.example.roamreserve.ui

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.example.roamreserve.designsystem.databinding.PeculiarityChipBinding
import com.example.roamreserve.domain.entity.Room
import com.example.roamreserve.formatCurrency
import com.example.roamreserve.room.databinding.RoomItemBinding

class RoomViewHolder(
    private val binding: RoomItemBinding,
    private val roomSelected: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private val context: Context = itemView.context

    fun bind(room: Room) {
        binding.nameTextView.text = room.name
        binding.priceTextVIew.text = formatCurrency(room.price)
        binding.priceAdditionalTextView.text = room.pricePerStay

        binding.selectRoomButton.setOnClickListener { roomSelected.invoke() }

        binding.peculiaritiesContainer.removeAllViews()

        val adapter = RoomViewPagerAdapter(room.imageUrls)
        binding.viewPager.adapter = adapter
        binding.pagerIndicator.setViewPager(binding.viewPager)

        for (peculiarity in room.peculiarities) {
            val cardViewBinding = PeculiarityChipBinding.inflate(
                LayoutInflater.from(context), binding.peculiaritiesContainer, false
            )
            cardViewBinding.peculiarityTextView.text = peculiarity
            binding.peculiaritiesContainer.addView(cardViewBinding.root)
        }
    }
}