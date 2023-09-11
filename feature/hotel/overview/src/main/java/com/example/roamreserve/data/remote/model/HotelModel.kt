package com.example.roamreserve.data.remote.model

import com.google.gson.annotations.SerializedName

data class HotelModel(
    val id: Int,
    val name: String,
    val adress: String,
    @SerializedName("minimal_price") val minimalPrice: Int,
    @SerializedName("price_for_it") val priceForIt: String,
    val rating: Int,
    @SerializedName("rating_name") val ratingName: String,
    @SerializedName("image_urls") val imageUrls: List<String>,
    @SerializedName("about_the_hotel") val aboutTheHotel: HotelDescription,
) {
    data class HotelDescription(
        val description: String,
        val peculiarities: List<String>
    )
}