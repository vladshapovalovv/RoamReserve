package com.example.roamreserve.domain.repository

import com.example.roamreserve.domain.entity.BookingInfo

interface BookingRepository {

    suspend fun get(): BookingInfo

}