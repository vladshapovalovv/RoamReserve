package com.example.roamreserve.presentation

import com.example.roamreserve.domain.entity.Hotel

sealed interface HotelState {

    data object Initial :HotelState

    data object Loading : HotelState

    data class Content(val hotels: List<Hotel>) : HotelState

    sealed interface Error : HotelState {

        data object NoConnection : Error

        data object ServerError : Error

        data class Unknown(val message: String) : Error
    }
}