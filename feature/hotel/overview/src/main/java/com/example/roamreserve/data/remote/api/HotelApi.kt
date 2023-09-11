package com.example.roamreserve.data.remote.api

import com.example.roamreserve.data.remote.model.HotelModel
import retrofit2.http.GET

interface HotelApi {

    @GET("35e0d18e-2521-4f1b-a575-f0fe366f66e3")
    suspend fun get(): HotelModel

}