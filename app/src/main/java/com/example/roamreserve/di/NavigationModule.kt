package com.example.roamreserve.di

import com.example.roamreserve.navigation.BookingRouterImpl
import com.example.roamreserve.navigation.HotelRouterImpl
import com.example.roamreserve.navigation.RoomRouterImpl
import com.example.roamreserve.navigation.SuccessRouterImpl
import com.example.roamreserve.presentation.BookingRouter
import com.example.roamreserve.presentation.HotelRouter
import com.example.roamreserve.presentation.RoomRouter
import com.example.roamreserve.presentation.SuccessRouter
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {

    companion object {
        @Provides
        @Singleton
        fun provideCicerone(): Cicerone<Router> = Cicerone.create()

        @Provides
        @Singleton
        fun provideRouter(cicerone: Cicerone<Router>): Router = cicerone.router

        @Provides
        @Singleton
        fun provideNavigatorHolder(cicerone: Cicerone<Router>): NavigatorHolder =
            cicerone.getNavigatorHolder()
    }

    @Binds
    @Singleton
    fun bindBookingRouter(impl: BookingRouterImpl): BookingRouter

    @Binds
    @Singleton
    fun bindHotelRouter(impl: HotelRouterImpl): HotelRouter

    @Binds
    @Singleton
    fun bindRoomRouter(impl: RoomRouterImpl): RoomRouter

    @Binds
    @Singleton
    fun bindSuccessRouter(impl: SuccessRouterImpl): SuccessRouter
}