package com.example.roamreserve.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roamreserve.domain.usecase.GetHotelsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class HotelViewModel @Inject constructor(
    private val getHotelsUseCase: GetHotelsUseCase,
    private val router: HotelRouter
) : ViewModel() {

    private val _state = MutableLiveData<HotelState>(HotelState.Initial)
    val state: LiveData<HotelState> = _state

     fun getHotels() {
        _state.value = HotelState.Loading

        viewModelScope.launch(handleError) {
            val hotels = getHotelsUseCase.invoke()
            _state.value = HotelState.Content(hotels)
        }
    }

    fun navigateToRooms() = router.openHotelRooms()

    private val handleError = CoroutineExceptionHandler { _, exception ->
        when (exception) {

            is IOException -> _state.value = HotelState.Error.NoConnection

            is HttpException-> _state.value = HotelState.Error.ServerError

            else -> _state.value =
                HotelState.Error.Unknown(exception.message ?: exception.toString())
        }
    }
}