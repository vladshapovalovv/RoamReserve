package com.example.roamreserve.navigation

import com.example.roamreserve.presentation.SuccessRouter
import com.example.roamreserve.ui.getHotelScreen
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class SuccessRouterImpl @Inject constructor(
    private val router: Router
) : SuccessRouter {
    override fun returnHome() {
        router.backTo(getHotelScreen())
    }
    override fun navigateBack() {
        router.exit()
    }
}