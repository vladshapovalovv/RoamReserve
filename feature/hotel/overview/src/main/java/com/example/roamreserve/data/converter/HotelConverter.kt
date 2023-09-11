package com.example.roamreserve.data.converter

import com.example.roamreserve.data.remote.model.HotelModel
import com.example.roamreserve.domain.entity.Hotel
import javax.inject.Inject

class HotelConverter @Inject constructor(){

    fun convert(from: HotelModel): Hotel {
        return Hotel(
            id = from.id,
            name = from.name,
            address = from.adress,
            minimalPrice = from.minimalPrice,
            priceType = from.priceForIt,
            rating = from.rating,
            ratingDescription = from.ratingName,
            imageUrls = from.imageUrls,
            description = from.aboutTheHotel.description,
            peculiarities = from.aboutTheHotel.peculiarities
        )
    }
}