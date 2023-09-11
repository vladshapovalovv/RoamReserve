package com.example.roamreserve.presentation

import com.example.roamreserve.domain.entity.BookingInfo

sealed interface BookingState {

    data object Initial : BookingState

    data object Loading : BookingState

    data class Content(val data: BookingInfo) : BookingState

    sealed interface Error : BookingState {

        data object NoConnection : Error

        data object ServerError : Error

        data class Unknown(val message: String) : Error
    }

}