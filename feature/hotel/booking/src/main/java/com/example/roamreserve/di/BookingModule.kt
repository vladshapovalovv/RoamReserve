package com.example.roamreserve.di

import com.example.roamreserve.data.datasource.BookingRemoteDataSource
import com.example.roamreserve.data.datasource.BookingRemoteDataSourceImpl
import com.example.roamreserve.data.remote.api.BookingApi
import com.example.roamreserve.data.repository.BookingRepositoryImpl
import com.example.roamreserve.domain.repository.BookingRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface BookingModule {

    companion object{

        @Provides
        @Singleton
        fun provideBookingApi(retrofit: Retrofit): BookingApi{
            return retrofit.create(BookingApi::class.java)
        }
    }

    @Singleton
    @Binds
    fun bindBookingRepository(impl: BookingRepositoryImpl): BookingRepository

    @Singleton
    @Binds
    fun bindBookingRemoteDataSource(impl: BookingRemoteDataSourceImpl): BookingRemoteDataSource

}