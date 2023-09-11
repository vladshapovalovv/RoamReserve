package com.example.roamreserve.domain.usecase

import com.example.roamreserve.domain.entity.Hotel
import com.example.roamreserve.domain.repository.HotelRepository
import javax.inject.Inject

class GetHotelsUseCase @Inject constructor(
    private val repository: HotelRepository
) : suspend () -> List<Hotel> by repository::get