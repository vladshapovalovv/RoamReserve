package com.example.roamreserve.presentation

import com.example.roamreserve.domain.entity.Room

sealed interface RoomState {

    data object Initial : RoomState

    data object Loading : RoomState

    data class Content(val rooms: List<Room>) : RoomState

    sealed interface Error : RoomState {

        data object NoConnection : Error

        data object ServerError : Error

        data class Unknown(val message: String) : Error
    }
}