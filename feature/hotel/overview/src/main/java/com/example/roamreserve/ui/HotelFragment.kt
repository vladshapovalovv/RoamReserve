package com.example.roamreserve.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.roamreserve.data.converter.HotelItemConverter
import com.example.roamreserve.designsystem.R.string
import com.example.roamreserve.overview.R
import com.example.roamreserve.overview.databinding.FragmentHotelBinding
import com.example.roamreserve.presentation.HotelState
import com.example.roamreserve.presentation.HotelViewModel
import com.example.roamreserve.showSnackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HotelFragment : Fragment(R.layout.fragment_hotel) {

    private val viewModel: HotelViewModel by viewModels()

    private val binding by viewBinding(FragmentHotelBinding::bind)

    private var adapter: HotelAdapter? = null

    @Inject
    lateinit var converter: HotelItemConverter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getHotels()

        initAdapters()
        initObservers()
        initListeners()
    }

    private fun initAdapters() {
        adapter = HotelAdapter()
        binding.recyclerView.adapter = adapter
    }

    private fun initObservers() {
        viewModel.state.observe(viewLifecycleOwner, ::applyState)
    }

    private fun initListeners() {
        binding.chooseRoomButton.setOnClickListener { viewModel.navigateToRooms() }
    }


    private fun applyState(state: HotelState) {
        when (state) {
            HotelState.Initial,
            HotelState.Loading -> applyLoadingState()

            is HotelState.Content -> applyContentState(state)

            is HotelState.Error -> applyErrorState(state)
        }
    }

    private fun applyLoadingState() {
        binding.progressBar.isVisible = true
        binding.recyclerView.isVisible = false
        binding.buttonLayout.isVisible = false

    }

    private fun applyContentState(state: HotelState.Content) {
        binding.progressBar.isGone = true
        binding.recyclerView.isVisible = true
        binding.buttonLayout.isVisible = true

        adapter?.items = converter.convert(state.hotels)
    }

    private fun applyErrorState(state: HotelState.Error) {
        binding.progressBar.isGone = true
        binding.recyclerView.isVisible = false
        binding.buttonLayout.isVisible = false

        when (state) {
            is HotelState.Error.ServerError -> showSnackbar(binding, getString(string.server_error))

            is HotelState.Error.NoConnection -> showSnackbar(
                binding,
                getString(string.connection_error)
            )

            is HotelState.Error.Unknown -> showSnackbar(
                binding,
                getString(string.unknown_error, state.message)
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        adapter = null
    }
}