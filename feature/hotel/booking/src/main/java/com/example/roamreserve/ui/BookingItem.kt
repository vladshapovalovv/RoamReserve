package com.example.roamreserve.ui

sealed class BookingItem {

    data class HotelInfoItem(
        val name: String,
        val rating: Int,
        val ratingDescription: String,
        val address: String
    ) : BookingItem()

    data class TravelInfoItem(
        val departure: String,
        val arrival: String,
        val date: String,
        val nights: String,
        val hotelName: String,
        val numberName: String,
        val tariff: String
    ) : BookingItem()

    data object BuyerInfo : BookingItem()

    data class TouristInfo(var count: Int) : BookingItem()

    data object AddTouristItem : BookingItem()

    data class TourPaymentInfo(
        val tourPrice: String,
        val fuelChargePrice: String,
        val serviceCharge: String,
        val totalCost: String
    ) : BookingItem()
}