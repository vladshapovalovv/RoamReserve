package com.example.roamreserve.ui

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.roamreserve.designsystem.databinding.PeculiarityChipBinding
import com.example.roamreserve.formatCurrency
import com.example.roamreserve.overview.R
import com.example.roamreserve.overview.databinding.HotelDescriptionItemBinding
import com.example.roamreserve.overview.databinding.HotelItemBinding

sealed class HotelViewHolder(
    binding: ViewBinding
) : RecyclerView.ViewHolder(binding.root) {

    class MainInfoViewHolder(private val binding: HotelItemBinding) : HotelViewHolder(binding) {

        private val context = itemView.context

        fun bind(mainInfo: HotelItem.MainInfo) {
            with(binding) {
                nameTextView.text = mainInfo.name
                addressTextView.text = mainInfo.address
                priceTextVIew.text =
                    context.getString(R.string.price_from, formatCurrency(mainInfo.price.toInt()))
                priceAdditionalTextView.text = mainInfo.priceDescription
                ratingChip.ratingTextView.text = buildString {
                    append(mainInfo.rating)
                    append(" ")
                    append(mainInfo.ratingDescription)
                }
                val adapter = HotelViewPagerAdapter(mainInfo.images)
                viewPager.adapter = adapter
                pagerIndicator.setViewPager(viewPager)
            }
        }
    }

    class AdditionalInfoViewHolder(
        private val binding: HotelDescriptionItemBinding,
    ) : HotelViewHolder(binding) {
        private val context: Context = itemView.context

        fun bind(additionalInfo: HotelItem.AdditionalInfo) {
            with(binding) {
                AboutTextView.text = additionalInfo.hotelDescription
                chipsContainer.removeAllViews()

                for (peculiarity in additionalInfo.peculiarities) {
                    val cardViewBinding = PeculiarityChipBinding.inflate(
                        LayoutInflater.from(context), chipsContainer, false
                    )
                    cardViewBinding.peculiarityTextView.text = peculiarity
                    chipsContainer.addView(cardViewBinding.root)
                }
            }
        }
    }
}