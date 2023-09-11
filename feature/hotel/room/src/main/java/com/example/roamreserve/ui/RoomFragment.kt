package com.example.roamreserve.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.roamreserve.designsystem.R.string
import com.example.roamreserve.presentation.RoomState
import com.example.roamreserve.presentation.RoomViewModel
import com.example.roamreserve.room.R
import com.example.roamreserve.room.databinding.FragmentRoomBinding
import com.example.roamreserve.showSnackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoomFragment : Fragment(R.layout.fragment_room) {

    private val viewModel: RoomViewModel by viewModels()

    private val binding by viewBinding(FragmentRoomBinding::bind)

    private var adapter: RoomAdapter? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapters()
        initObservers()
        initListeners()
        viewModel.getRooms()
    }


    private fun initAdapters() {
        adapter = RoomAdapter(viewModel::navigateToDetails)
        binding.recyclerView.adapter = adapter
    }

    private fun initListeners() {
        binding.backButton.setOnClickListener { viewModel.navigateBack() }
    }

    private fun initObservers() {
        viewModel.state.observe(viewLifecycleOwner, ::applyState)
    }

    private fun applyState(state: RoomState) {
        when (state) {
            RoomState.Initial,
            RoomState.Loading -> applyLoadingState()

            is RoomState.Content -> applyContentState(state)

            is RoomState.Error -> applyErrorState(state)
        }

    }

    private fun applyLoadingState() {
        binding.progressBar.isVisible = true
        binding.recyclerView.isVisible = false
    }

    private fun applyContentState(state: RoomState.Content) {
        binding.progressBar.isGone = true
        binding.recyclerView.isVisible = true
        adapter?.items = state.rooms

    }

    private fun applyErrorState(state: RoomState.Error) {
        binding.progressBar.isGone = true
        binding.recyclerView.isVisible = false

        when (state) {
            is RoomState.Error.ServerError -> showSnackbar(
                binding,
                getString(string.server_error)
            )

            is RoomState.Error.NoConnection -> showSnackbar(
                binding,
                getString(string.connection_error)
            )

            is RoomState.Error.Unknown -> showSnackbar(
                binding,
                getString(
                    string.unknown_error,
                    state.message
                )
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        adapter = null
    }
}