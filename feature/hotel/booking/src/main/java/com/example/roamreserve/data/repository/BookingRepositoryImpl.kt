package com.example.roamreserve.data.repository

import com.example.roamreserve.data.datasource.BookingRemoteDataSource
import com.example.roamreserve.domain.entity.BookingInfo
import com.example.roamreserve.domain.repository.BookingRepository
import javax.inject.Inject

class BookingRepositoryImpl @Inject constructor(
    private val dataSource: BookingRemoteDataSource
) : BookingRepository {

    override suspend fun get(): BookingInfo =
        dataSource.get()
}