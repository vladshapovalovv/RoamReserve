package com.example.roamreserve.navigation

import com.example.roamreserve.presentation.BookingRouter
import com.example.roamreserve.ui.getRoomScreen
import com.example.roamreserve.ui.getSuccessScreen
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class BookingRouterImpl @Inject constructor(
    private val router: Router
): BookingRouter {

    override fun openSuccessScreen() {
        router.navigateTo(getSuccessScreen())
    }

    override fun popBack() {
        router.backTo(getRoomScreen())
    }
}