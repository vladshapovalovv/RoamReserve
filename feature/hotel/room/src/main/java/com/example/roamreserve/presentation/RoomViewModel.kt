package com.example.roamreserve.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roamreserve.domain.usecase.GetRoomsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor(
    private val getRoomsUseCase: GetRoomsUseCase,
    private val router: RoomRouter
) : ViewModel() {

    private val _state = MutableLiveData<RoomState>(RoomState.Initial)
    val state: LiveData<RoomState> = _state

    fun getRooms() {
        _state.value = RoomState.Loading

        viewModelScope.launch(handleError) {
            val rooms = getRoomsUseCase.invoke()
            _state.value = RoomState.Content(rooms)
        }
    }

    fun navigateToDetails() = router.openBookingDetails()

    fun navigateBack() = router.popBack()

    private val handleError = CoroutineExceptionHandler { _, exception ->
        when (exception) {

            is IOException -> _state.value = RoomState.Error.NoConnection

            is HttpException -> _state.value = RoomState.Error.ServerError

            else -> _state.value =
                RoomState.Error.Unknown(exception.message ?: exception.toString())
        }
    }

}