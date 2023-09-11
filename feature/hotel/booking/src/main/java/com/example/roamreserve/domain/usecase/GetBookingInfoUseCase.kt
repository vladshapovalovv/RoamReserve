package com.example.roamreserve.domain.usecase

import com.example.roamreserve.domain.entity.BookingInfo
import com.example.roamreserve.domain.repository.BookingRepository
import javax.inject.Inject

class GetBookingInfoUseCase @Inject constructor(
    private val repository: BookingRepository
) : suspend () -> BookingInfo by repository::get