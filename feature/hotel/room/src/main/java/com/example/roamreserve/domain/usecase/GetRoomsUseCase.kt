package com.example.roamreserve.domain.usecase

import com.example.roamreserve.domain.entity.Room
import com.example.roamreserve.domain.repository.HotelRoomRepository
import javax.inject.Inject

class GetRoomsUseCase @Inject constructor(
    private val repository: HotelRoomRepository
) : suspend () -> List<Room> by repository::get