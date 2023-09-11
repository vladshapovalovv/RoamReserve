package com.example.roamreserve.domain.repository

import com.example.roamreserve.domain.entity.Hotel

interface HotelRepository {

    suspend fun get(): List<Hotel>

}