package com.example.roamreserve.data.converter

import com.example.roamreserve.data.remote.model.BookingInfoModel
import com.example.roamreserve.domain.entity.BookingInfo
import javax.inject.Inject

class BookingConverter @Inject constructor() {

    fun convert(from: BookingInfoModel): BookingInfo{
        return BookingInfo(
            id = from.id,
            hotelName = from.hotelName,
            hotelAddress = from.hotelAddress,
            hotelRating = from.horating,
            ratingDetails = from.ratingName,
            departure = from.departure,
            arrival = from.arrivalCountry,
            tourStartDate = from.tourDateStart,
            tourEndDate = from.tourDateStop,
            numberOfStayNights = from.numberOfNights,
            roomDescription = from.room,
            serviceCharge = from.serviceCharge,
            serviceType = from.nutrition,
            tourPrice = from.tourPrice,
            fuelCharge = from.fuelCharge
        )
    }

}