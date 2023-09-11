package com.example.roamreserve.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.roamreserve.booking.R
import com.example.roamreserve.booking.databinding.AddItemBinding
import com.example.roamreserve.booking.databinding.BuyerDataItemBinding
import com.example.roamreserve.booking.databinding.DescriptionItemBinding
import com.example.roamreserve.booking.databinding.PaymentItemBinding
import com.example.roamreserve.booking.databinding.TouristItemBinding
import com.example.roamreserve.booking.databinding.TravelItemBinding

class BookingAdapter(
) : RecyclerView.Adapter<BookingViewHolder>() {

    var items = mutableListOf<BookingItem>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingViewHolder {
        return when (viewType) {
            R.layout.description_item -> BookingViewHolder.HotelDescriptionViewHolder(
                DescriptionItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            R.layout.travel_item -> BookingViewHolder.TravelInfoViewHolder(
                TravelItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            R.layout.buyer_data_item -> BookingViewHolder.BuyerInfoViewHolder(
                BuyerDataItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            R.layout.tourist_item -> BookingViewHolder.TouristInfoViewHolder(
                TouristItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            R.layout.add_item -> BookingViewHolder.AddTouristViewHolder(
                AddItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                addAnotherTourist = {
                    addTourist(
                        BookingItem.TouristInfo(
                            items.filterIsInstance<BookingItem.TouristInfo>().count()
                        )
                    )
                }
            )

            R.layout.payment_item -> BookingViewHolder.PaymentInfoViewHolder(
                PaymentItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            else -> throw IllegalArgumentException("Unsupported view type")
        }

    }

    override fun onBindViewHolder(holder: BookingViewHolder, position: Int) {
        when (holder) {

            is BookingViewHolder.HotelDescriptionViewHolder -> holder.bind(items[position] as BookingItem.HotelInfoItem)

            is BookingViewHolder.TravelInfoViewHolder -> holder.bind(items[position] as BookingItem.TravelInfoItem)

            is BookingViewHolder.PaymentInfoViewHolder -> holder.bind(items[position] as BookingItem.TourPaymentInfo)

            is BookingViewHolder.TouristInfoViewHolder -> holder.bind(items[position] as BookingItem.TouristInfo)
                .also {
                    holder.binding.expandButton.setOnClickListener {
                        if (holder.isExpanded) {
                            holder.binding.expandableLayout.isVisible = true
                        } else {
                            holder.binding.expandableLayout.isGone = true
                        }
                        holder.isExpanded = !holder.isExpanded
                        notifyItemChanged(position)
                    }
                }

            is BookingViewHolder.AddTouristViewHolder -> holder.bind()

            is BookingViewHolder.BuyerInfoViewHolder -> holder.bind()
        }

    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {

            is BookingItem.HotelInfoItem -> R.layout.description_item

            is BookingItem.TravelInfoItem -> R.layout.travel_item

            is BookingItem.BuyerInfo -> R.layout.buyer_data_item

            is BookingItem.TouristInfo -> R.layout.tourist_item

            is BookingItem.AddTouristItem -> R.layout.add_item

            is BookingItem.TourPaymentInfo -> R.layout.payment_item
        }
    }

    override fun getItemCount() = items.size

    private fun addTourist(current: BookingItem.TouristInfo) {
        val tourist = BookingItem.TouristInfo(1 + current.count++)
        if (items.size >= 2) {
            items.add(items.size - 2, tourist)
            notifyItemInserted(items.size - 2)
        } else {
            items.add(tourist)
            notifyItemInserted(items.size - 1)
        }
    }

}