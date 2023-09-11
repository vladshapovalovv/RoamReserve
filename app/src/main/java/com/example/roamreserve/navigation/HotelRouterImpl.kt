package com.example.roamreserve.navigation

import com.example.roamreserve.presentation.HotelRouter
import com.example.roamreserve.ui.getHotelScreen
import com.example.roamreserve.ui.getRoomScreen
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class HotelRouterImpl @Inject constructor(
    private val router: Router
) : HotelRouter {

    override fun openHotelRooms() {
        router.navigateTo(getRoomScreen())
    }
}