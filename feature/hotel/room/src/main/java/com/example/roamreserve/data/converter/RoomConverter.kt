package com.example.roamreserve.data.converter

import com.example.roamreserve.data.remote.model.RoomModel
import com.example.roamreserve.domain.entity.Room
import javax.inject.Inject

class RoomConverter @Inject constructor() {

    fun convert(from: RoomModel): Room {
        return Room(
            id = from.id,
            name = from.name,
            price = from.price,
            pricePerStay = from.pricePer,
            peculiarities = from.peculiarities,
            imageUrls = from.imageUrls
        )
    }
}