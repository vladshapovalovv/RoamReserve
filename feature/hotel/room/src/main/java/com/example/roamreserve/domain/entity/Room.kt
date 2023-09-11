package com.example.roamreserve.domain.entity

data class Room(
    val id: Int,
    val name: String,
    val price: Int,
    val pricePerStay: String,
    val peculiarities: List<String>,
    val imageUrls: List<String>
)
