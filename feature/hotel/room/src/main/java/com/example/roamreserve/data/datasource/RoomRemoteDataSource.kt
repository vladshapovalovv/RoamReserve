package com.example.roamreserve.data.datasource

import com.example.roamreserve.data.converter.RoomConverter
import com.example.roamreserve.data.remote.api.RoomApi
import com.example.roamreserve.domain.entity.Room
import javax.inject.Inject

interface RoomRemoteDataSource {

    suspend fun get(): List<Room>
}

class RoomRemoteDataSourceImpl @Inject constructor(
    private val api: RoomApi,
    private val converter: RoomConverter
) : RoomRemoteDataSource {

    override suspend fun get(): List<Room> =
        api.getAll().rooms.map(converter::convert)
}