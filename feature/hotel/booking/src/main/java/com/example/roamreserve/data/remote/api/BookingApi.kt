package com.example.roamreserve.data.remote.api

import com.example.roamreserve.data.remote.model.BookingInfoModel
import retrofit2.http.GET

interface BookingApi {

    @GET("e8868481-743f-4eb2-a0d7-2bc4012275c8")
    suspend fun getInfo(): BookingInfoModel

}