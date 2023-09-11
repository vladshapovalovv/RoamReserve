package com.example.roamreserve.di

import com.example.roamreserve.data.datasource.HotelRemoteDataSource
import com.example.roamreserve.data.datasource.HotelRemoteDataSourceImpl
import com.example.roamreserve.data.remote.api.HotelApi
import com.example.roamreserve.data.repository.HotelRepositoryImpl
import com.example.roamreserve.domain.repository.HotelRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface HotelModule {

    companion object{

        @Provides
        @Singleton
        fun provideHotelApi(retrofit: Retrofit): HotelApi{
            return retrofit.create(HotelApi::class.java)
        }
    }

    @Singleton
    @Binds
    fun bindHotelRepository(impl: HotelRepositoryImpl): HotelRepository

    @Singleton
    @Binds
    fun bindHotelRemoteDataSource(impl: HotelRemoteDataSourceImpl): HotelRemoteDataSource
}