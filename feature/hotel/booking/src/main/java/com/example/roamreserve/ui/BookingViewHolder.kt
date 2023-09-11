package com.example.roamreserve.ui

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.roamreserve.booking.R
import com.example.roamreserve.booking.databinding.AddItemBinding
import com.example.roamreserve.booking.databinding.BuyerDataItemBinding
import com.example.roamreserve.booking.databinding.DescriptionItemBinding
import com.example.roamreserve.booking.databinding.PaymentItemBinding
import com.example.roamreserve.booking.databinding.TouristItemBinding
import com.example.roamreserve.booking.databinding.TravelItemBinding
import com.example.roamreserve.createBirthDateMaskTextWatcher
import com.example.roamreserve.createPhoneMaskTextWatcher
import com.google.android.material.textfield.TextInputEditText

sealed class BookingViewHolder(
    binding: ViewBinding
) : RecyclerView.ViewHolder(binding.root) {

    val context: Context = itemView.context

    class HotelDescriptionViewHolder(private val binding: DescriptionItemBinding) :
        BookingViewHolder(binding) {
        fun bind(hotelInfo: BookingItem.HotelInfoItem) {
            binding.nameText.text = hotelInfo.name
            binding.addressText.text = hotelInfo.address
            binding.ratingChip.ratingTextView.text = buildString {
                append(hotelInfo.rating)
                append(" ")
                append(hotelInfo.ratingDescription)
            }
        }
    }

    class TravelInfoViewHolder(private val binding: TravelItemBinding) :
        BookingViewHolder(binding) {
        fun bind(travelInfo: BookingItem.TravelInfoItem) {
            binding.arrivalTextView.text = travelInfo.arrival
            binding.departureTextView.text = travelInfo.departure
            binding.dateTextView.text = travelInfo.date
            binding.nightsTextView.text =
                context.getString(R.string.nights_count, travelInfo.nights)
            binding.hotelTextView.text = travelInfo.hotelName
            binding.roomTextView.text = travelInfo.numberName
            binding.tariffTextView.text = travelInfo.tariff
        }
    }

    class BuyerInfoViewHolder(
        private val binding: BuyerDataItemBinding
    ) : BookingViewHolder(binding) {
        fun bind() {
            binding.numberEditText.addTextChangedListener(createPhoneMaskTextWatcher(binding.numberEditText))
        }
        fun checkIfFieldsValid() = handleInput(
            editTexts = listOf(
                binding.numberEditText, binding.mailEditText
            )
        )
    }

    class TouristInfoViewHolder(
        val binding: TouristItemBinding,
        var isExpanded: Boolean = false,
    ) : BookingViewHolder(binding) {
        fun bind(touristInfo: BookingItem.TouristInfo) {

            binding.birthDateEditText.addTextChangedListener(
                createBirthDateMaskTextWatcher(binding.birthDateEditText)
            )

            when (touristInfo.count) {
                1 -> binding.touristTextView.text = context.getString(R.string.count_first)

                2 -> binding.touristTextView.text = context.getString(R.string.count_second)

                3 -> binding.touristTextView.text = context.getString(R.string.count_third)

                4 -> binding.touristTextView.text = context.getString(R.string.count_forth)

                5 -> binding.touristTextView.text = context.getString(R.string.count_fifth)

                else -> {
                    binding.touristTextView.text = buildString {
                        append(context.getString(R.string.tourist_count))
                        append(touristInfo.count)
                    }
                }
            }
        }

        fun checkIfFieldsValid() = handleInput(
            editTexts = listOf(
                binding.nameEditText,
                binding.secondNameEditText,
                binding.birthDateEditText,
                binding.citizenshipEditText,
                binding.passportNumberEditText,
                binding.passportExpiryDateEditText
            )
        )
    }

    class AddTouristViewHolder(
        private val binding: AddItemBinding,
        private val addAnotherTourist: () -> Unit
    ) : BookingViewHolder(binding) {
        fun bind() {
            binding.addTouristButton.setOnClickListener { addAnotherTourist.invoke() }
        }
    }

    class PaymentInfoViewHolder(private val binding: PaymentItemBinding) :
        BookingViewHolder(binding) {
        fun bind(paymentInfo: BookingItem.TourPaymentInfo) {
            binding.serviceChargeTextView.text = paymentInfo.serviceCharge
            binding.fuelChargeTextView.text = paymentInfo.fuelChargePrice
            binding.tourPriceTextView.text = paymentInfo.tourPrice
            binding.summaryPriceTextView.text = paymentInfo.totalCost
        }
    }

    fun handleInput(editTexts: List<TextInputEditText>): Boolean {
        var isValid = true
        for (editText in editTexts) {
            if (editText.text.toString().trim().isEmpty()) {
                editText.error = context.getString(R.string.field_empty)
                isValid = false
            } else {
                editText.error = null
            }
        }
        return isValid
    }
}



