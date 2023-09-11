package com.example.roamreserve.ui

sealed class HotelItem{

    data class MainInfo(
        val images: List<String>,
        val rating: Int,
        val ratingDescription: String,
        val name: String,
        val address: String,
        val price: String,
        val priceDescription: String
    ): HotelItem()

    data class AdditionalInfo(
        val peculiarities: List<String>,
        val hotelDescription: String
    ): HotelItem()
}
