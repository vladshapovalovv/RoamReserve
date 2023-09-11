package com.example.roamreserve.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.roamreserve.booking.R
import com.example.roamreserve.booking.databinding.SuccessFragmentBinding
import com.example.roamreserve.presentation.SuccessRouter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SuccessFragment : Fragment(R.layout.success_fragment) {

    @Inject
    lateinit var router: SuccessRouter

    private val binding by viewBinding(SuccessFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backButton.setOnClickListener { router.navigateBack() }

        binding.okayButton.setOnClickListener { router.returnHome() }
    }
}