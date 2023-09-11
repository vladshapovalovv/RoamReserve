package com.example.roamreserve.data.datasource

import com.example.roamreserve.data.converter.HotelConverter
import com.example.roamreserve.data.remote.api.HotelApi
import com.example.roamreserve.domain.entity.Hotel
import javax.inject.Inject

interface HotelRemoteDataSource {

    suspend fun get(): List<Hotel>

}

class HotelRemoteDataSourceImpl @Inject constructor(
    private val api: HotelApi,
    private val converter: HotelConverter
) : HotelRemoteDataSource {

    override suspend fun get(): List<Hotel> {
        return listOf(converter.convert(api.get()))
    }

}