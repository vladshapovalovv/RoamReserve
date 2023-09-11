package com.example.roamreserve.data.repository

import com.example.roamreserve.data.remote.api.RoomApi
import com.example.roamreserve.data.converter.RoomConverter
import com.example.roamreserve.domain.entity.Room
import com.example.roamreserve.domain.repository.HotelRoomRepository
import javax.inject.Inject

class HotelRoomRepositoryImpl @Inject constructor(
    private val api: RoomApi,
    private val converter: RoomConverter
) : HotelRoomRepository {
    override suspend fun get(): List<Room> {
        return api.getAll().rooms.map {
            converter.convert(it)
        }
    }
}