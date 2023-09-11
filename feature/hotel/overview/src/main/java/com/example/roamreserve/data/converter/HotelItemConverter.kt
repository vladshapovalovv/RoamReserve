package com.example.roamreserve.data.converter

import com.example.roamreserve.domain.entity.Hotel
import com.example.roamreserve.ui.HotelItem
import javax.inject.Inject

class HotelItemConverter @Inject constructor() {

    fun convert(hotels: List<Hotel>): List<HotelItem> {
        val hotelItems = mutableListOf<HotelItem>()
        for (hotel in hotels) {
            val mainInfoItem = HotelItem.MainInfo(
                images = hotel.imageUrls,
                rating = hotel.rating,
                ratingDescription = hotel.ratingDescription,
                name = hotel.name,
                address = hotel.address,
                price = hotel.minimalPrice.toString(),
                priceDescription = hotel.priceType
            )
            val additionalInfoItem = HotelItem.AdditionalInfo(
                peculiarities = hotel.peculiarities,
                hotelDescription = hotel.description
            )
            hotelItems.add(mainInfoItem)
            hotelItems.add(additionalInfoItem)
        }
        return hotelItems
    }
}