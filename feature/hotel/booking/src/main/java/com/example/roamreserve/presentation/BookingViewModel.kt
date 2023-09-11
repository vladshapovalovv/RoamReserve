package com.example.roamreserve.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roamreserve.domain.usecase.GetBookingInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class BookingViewModel @Inject constructor(
    private val getBookingInfoUseCase: GetBookingInfoUseCase,
    private val router: BookingRouter
) : ViewModel() {

    private val _state = MutableLiveData<BookingState>(BookingState.Initial)
    val state: LiveData<BookingState> = _state

    fun getBookingInfo() {
        _state.value = BookingState.Loading

        viewModelScope.launch(handleError) {
            val bookingInfo = getBookingInfoUseCase.invoke()
            _state.value = BookingState.Content(bookingInfo)
        }
    }

    private val handleError = CoroutineExceptionHandler { _, exception ->
        when (exception) {

            is IOException -> _state.value = BookingState.Error.NoConnection

            is HttpException -> _state.value = BookingState.Error.ServerError

            else -> _state.value =
                BookingState.Error.Unknown(exception.message ?: exception.toString())
        }
    }

    fun navigateToSuccessScreen() = router.openSuccessScreen()

    fun navigateBack() = router.popBack()
}