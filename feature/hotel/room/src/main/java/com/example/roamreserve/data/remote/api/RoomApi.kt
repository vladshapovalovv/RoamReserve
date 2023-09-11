package com.example.roamreserve.data.remote.api

import com.example.roamreserve.data.remote.model.RoomResponse
import retrofit2.http.GET

interface RoomApi {

    @GET("f9a38183-6f95-43aa-853a-9c83cbb05ecd")
    suspend fun getAll(): RoomResponse

}