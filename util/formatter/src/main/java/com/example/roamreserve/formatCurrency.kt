package com.example.roamreserve

import java.text.NumberFormat
import java.util.Locale

fun formatCurrency(value: Int): String {
    val locale = Locale("ru", "RU")
    val currencyFormat = NumberFormat.getCurrencyInstance(locale)
    return currencyFormat.format(value).replaceFirst(Regex(",00"), "")
}