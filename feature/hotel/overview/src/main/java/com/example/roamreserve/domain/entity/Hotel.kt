package com.example.roamreserve.domain.entity

data class Hotel(
    val id: Int,
    val name: String,
    val address: String,
    val minimalPrice: Int,
    val priceType: String,
    val rating: Int,
    val ratingDescription: String,
    val imageUrls: List<String>,
    val description: String,
    val peculiarities: List<String>
)
