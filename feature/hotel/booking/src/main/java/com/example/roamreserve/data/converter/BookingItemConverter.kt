package com.example.roamreserve.data.converter

import com.example.roamreserve.domain.entity.BookingInfo
import com.example.roamreserve.formatCurrency
import com.example.roamreserve.ui.BookingItem
import javax.inject.Inject

class BookingItemConverter @Inject constructor() {

    fun convert(from: BookingInfo): MutableList<BookingItem> {
        val items = mutableListOf<BookingItem>()
        items.add(
            BookingItem.HotelInfoItem(
                name = from.hotelName,
                rating = from.hotelRating,
                ratingDescription = from.ratingDetails,
                address = from.hotelAddress
            )
        )

        items.add(
            BookingItem.TravelInfoItem(
                departure = from.departure,
                arrival = from.arrival,
                date = "${from.tourStartDate} - ${from.tourEndDate}",
                nights = from.numberOfStayNights.toString(),
                hotelName = from.hotelName,
                numberName = from.roomDescription,
                tariff = from.serviceType
            )
        )

        items.add(BookingItem.BuyerInfo)

        items.add(BookingItem.TouristInfo(1))

        items.add(BookingItem.AddTouristItem)

        items.add(
            BookingItem.TourPaymentInfo(
                tourPrice = formatCurrency(from.tourPrice),
                fuelChargePrice = formatCurrency(from.fuelCharge),
                serviceCharge = formatCurrency(from.serviceCharge),
                totalCost = formatCurrency(calculateTotalCost(from))
            )
        )
        return items
    }

    private fun calculateTotalCost(bookingInfo: BookingInfo) =
        with(bookingInfo) { tourPrice + fuelCharge + serviceCharge }

}

