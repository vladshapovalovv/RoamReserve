package com.example.roamreserve.navigation

import com.example.roamreserve.presentation.RoomRouter
import com.example.roamreserve.ui.getBookingScreen
import com.example.roamreserve.ui.getHotelScreen
import com.example.roamreserve.ui.getRoomScreen
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class RoomRouterImpl @Inject constructor(
    private val router: Router
) : RoomRouter {

    override fun openBookingDetails() {
        router.navigateTo(getBookingScreen())
    }

    override fun popBack() {
        router.backTo(getHotelScreen())
    }
}