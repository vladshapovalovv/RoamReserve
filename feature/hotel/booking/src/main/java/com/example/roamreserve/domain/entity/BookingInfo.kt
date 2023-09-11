package com.example.roamreserve.domain.entity

data class BookingInfo(
    val id: Int,
    val hotelName: String,
    val hotelAddress: String,
    val hotelRating: Int,
    val ratingDetails: String,
    val departure: String,
    val arrival: String,
    val tourStartDate: String,
    val tourEndDate: String,
    val numberOfStayNights: Int,
    val roomDescription: String,
    val serviceType: String,
    val tourPrice: Int,
    val fuelCharge: Int,
    val serviceCharge: Int
)
