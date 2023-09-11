package com.example.roamreserve.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roamreserve.overview.R
import com.example.roamreserve.overview.databinding.HotelDescriptionItemBinding
import com.example.roamreserve.overview.databinding.HotelItemBinding

class HotelAdapter : RecyclerView.Adapter<HotelViewHolder>() {

    var items = listOf<HotelItem>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelViewHolder {
        return when (viewType) {
            R.layout.hotel_item -> HotelViewHolder.MainInfoViewHolder(
                HotelItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            R.layout.hotel_description_item -> HotelViewHolder.AdditionalInfoViewHolder(
                HotelDescriptionItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
            )
            else -> throw IllegalArgumentException("Unsupported view type")
        }
    }

    override fun onBindViewHolder(holder: HotelViewHolder, position: Int) {
        when (holder) {
            is HotelViewHolder.MainInfoViewHolder -> holder.bind(items[position] as HotelItem.MainInfo)

            is HotelViewHolder.AdditionalInfoViewHolder -> holder.bind(items[position] as HotelItem.AdditionalInfo)
        }
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {

            is HotelItem.MainInfo -> R.layout.hotel_item

            is HotelItem.AdditionalInfo -> R.layout.hotel_description_item
        }
    }
}