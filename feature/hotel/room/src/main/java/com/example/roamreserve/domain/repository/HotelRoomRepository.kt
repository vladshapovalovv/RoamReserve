package com.example.roamreserve.domain.repository

import com.example.roamreserve.domain.entity.Room

interface HotelRoomRepository {

    suspend fun get(): List<Room>

}