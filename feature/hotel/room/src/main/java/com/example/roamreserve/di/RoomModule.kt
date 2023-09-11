package com.example.roamreserve.di

import com.example.roamreserve.data.datasource.RoomRemoteDataSource
import com.example.roamreserve.data.datasource.RoomRemoteDataSourceImpl
import com.example.roamreserve.data.remote.api.RoomApi
import com.example.roamreserve.data.repository.HotelRoomRepositoryImpl
import com.example.roamreserve.domain.repository.HotelRoomRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface RoomModule {

    companion object {

        @Provides
        @Singleton
        fun provideRoomApi(retrofit: Retrofit): RoomApi {
            return retrofit.create(RoomApi::class.java)
        }
    }

    @Singleton
    @Binds
    fun bindHotelRepository(impl: HotelRoomRepositoryImpl): HotelRoomRepository

    @Singleton
    @Binds
    fun bindRoomRemoteDataSource(impl: RoomRemoteDataSourceImpl): RoomRemoteDataSource

}