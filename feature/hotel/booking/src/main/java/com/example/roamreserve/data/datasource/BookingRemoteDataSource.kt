package com.example.roamreserve.data.datasource

import com.example.roamreserve.data.converter.BookingConverter
import com.example.roamreserve.data.remote.api.BookingApi
import com.example.roamreserve.domain.entity.BookingInfo
import javax.inject.Inject

interface BookingRemoteDataSource {

    suspend fun get(): BookingInfo
}

class BookingRemoteDataSourceImpl @Inject constructor(
    private val api: BookingApi,
    private val converter: BookingConverter
) : BookingRemoteDataSource {

    override suspend fun get(): BookingInfo =
        converter.convert(api.getInfo())

}