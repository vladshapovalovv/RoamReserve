package com.example.roamreserve.data.repository

import com.example.roamreserve.data.datasource.HotelRemoteDataSource
import com.example.roamreserve.domain.entity.Hotel
import com.example.roamreserve.domain.repository.HotelRepository
import javax.inject.Inject

class HotelRepositoryImpl @Inject constructor(
    private val dataSource: HotelRemoteDataSource
) : HotelRepository {

    override suspend fun get(): List<Hotel> =
        dataSource.get()
}