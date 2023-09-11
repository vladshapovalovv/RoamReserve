package com.example.roamreserve.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.roamreserve.booking.R
import com.example.roamreserve.booking.databinding.FragmentBookingBinding
import com.example.roamreserve.data.converter.BookingItemConverter
import com.example.roamreserve.designsystem.R.string
import com.example.roamreserve.presentation.BookingState
import com.example.roamreserve.presentation.BookingViewModel
import com.example.roamreserve.showSnackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BookingFragment : Fragment(R.layout.fragment_booking) {

    private val viewModel: BookingViewModel by viewModels()

    private val binding by viewBinding(FragmentBookingBinding::bind)

    private var adapter: BookingAdapter? = null

    @Inject
    lateinit var converter: BookingItemConverter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getBookingInfo()

        initAdapters()
        initObservers()
        initListeners()
    }

    private fun initAdapters() {
        adapter = BookingAdapter()
        binding.recyclerView.adapter = adapter
    }

    private fun initObservers() {
        viewModel.state.observe(viewLifecycleOwner, ::applyState)
    }

    private fun initListeners() {
        binding.confirmPayButton.setOnClickListener {
            if (isBuyerInputValid() && isTouristInputValid()) {
                viewModel.navigateToSuccessScreen()
            }
        }
        binding.backButton.setOnClickListener { viewModel.navigateBack() }
    }

    private fun applyState(state: BookingState) {
        when (state) {
            BookingState.Initial,
            BookingState.Loading -> applyLoadingState()

            is BookingState.Content -> applyContentState(state)

            is BookingState.Error -> applyErrorState(state)
        }
    }

    private fun applyLoadingState() {
        binding.progressBar.isVisible = true
        binding.recyclerView.isVisible = false
        binding.buttonLayout.isVisible = false
    }

    private fun applyContentState(state: BookingState.Content) {
        binding.progressBar.isGone = true
        binding.recyclerView.isVisible = true
        binding.buttonLayout.isVisible = true

        adapter?.items = converter.convert(state.data).also { items ->
            binding.confirmPayButton.text = buildString {
                append(getString(R.string.pay))
                append(
                    items.filterIsInstance<BookingItem.TourPaymentInfo>()
                        .joinToString { it.totalCost })
            }
        }
    }

    private fun applyErrorState(state: BookingState.Error) {
        binding.progressBar.isGone = true
        binding.recyclerView.isVisible = false
        binding.buttonLayout.isVisible = false

        when (state) {
            is BookingState.Error.ServerError -> showSnackbar(
                binding,
                getString(string.server_error)
            )

            is BookingState.Error.NoConnection -> showSnackbar(
                binding,
                getString(string.connection_error)
            )

            is BookingState.Error.Unknown -> showSnackbar(
                binding,
                getString(string.unknown_error, state.message)
            )
        }
    }

    private fun isBuyerInputValid(): Boolean {
        val viewHolder = binding.recyclerView.findViewHolderForAdapterPosition(2)
        if (viewHolder is BookingViewHolder.BuyerInfoViewHolder) {
            return viewHolder.checkIfFieldsValid()
        }
        return false
    }

    private fun isTouristInputValid(): Boolean {
        val viewHolder = binding.recyclerView.findViewHolderForAdapterPosition(3)
        if (viewHolder is BookingViewHolder.TouristInfoViewHolder) {
            return viewHolder.checkIfFieldsValid()
        }
        return false
    }


    override fun onDestroyView() {
        super.onDestroyView()
        adapter = null
    }
}