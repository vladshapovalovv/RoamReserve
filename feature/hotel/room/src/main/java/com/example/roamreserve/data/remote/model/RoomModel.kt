package com.example.roamreserve.data.remote.model

import com.google.gson.annotations.SerializedName

data class RoomResponse(
    val rooms: List<RoomModel>
)

data class RoomModel(
    val id: Int,
    val name: String,
    val price: Int,
    @SerializedName("price_per") val pricePer: String,
    val peculiarities: List<String>,
    @SerializedName("image_urls") val imageUrls: List<String>
)