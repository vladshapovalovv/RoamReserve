package com.example.roamreserve

import android.view.View
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar

fun showSnackbar(
    binding: ViewBinding,
    message: String,
    duration: Int = Snackbar.LENGTH_LONG
) {
    Snackbar.make(binding.root, message, duration).show()
}