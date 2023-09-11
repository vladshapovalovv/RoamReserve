package com.example.roamreserve

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun createPhoneMaskTextWatcher(editText: EditText): TextWatcher {
    return object : TextWatcher {
        private var isFormatting = false

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            if (isFormatting) return
            isFormatting = true

            val rawPhoneNumber = s?.toString()?.replace(Regex("[^\\d]"), "") ?: ""

            val formattedPhoneNumber = buildString {
                append("+7 (")

                for (i in rawPhoneNumber.indices) {
                    when (i) {
                        1, 2, 3 -> append(rawPhoneNumber[i])
                        4 -> append(") ${rawPhoneNumber[i]}")
                        5, 6 -> append(rawPhoneNumber[i])
                        7 -> append("-${rawPhoneNumber[i]}")
                        8 -> append(rawPhoneNumber[i])
                        9 -> append("-${rawPhoneNumber[i]}")
                        10 -> append(rawPhoneNumber[i])
                    }
                }
            }

            s?.replace(0, s.length, formattedPhoneNumber)

            val cursorPosition = if (s != null) {
                val oldSelectionPos = editText.selectionStart
                if (oldSelectionPos > formattedPhoneNumber.length) formattedPhoneNumber.length else oldSelectionPos
            } else {
                formattedPhoneNumber.length
            }

            editText.setSelection(cursorPosition)

            isFormatting = false
        }
    }
}

fun createBirthDateMaskTextWatcher(editText: EditText): TextWatcher {
    return object : TextWatcher {
        private var isFormatting = false

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            if (isFormatting) return
            isFormatting = true

            val rawDate = s?.toString()?.replace(Regex("[^\\d]"), "") ?: ""

            val formattedDate = buildString {
                for (i in rawDate.indices) {
                    when (i) {
                        2, 4 -> append(".")
                    }
                    append(rawDate[i])
                }
            }

            s?.replace(0, s.length, formattedDate)

            val cursorPosition = if (s != null) {
                val oldSelectionPos = editText.selectionStart
                if (oldSelectionPos > formattedDate.length) formattedDate.length else oldSelectionPos
            } else {
                formattedDate.length
            }

            editText.setSelection(cursorPosition)

            isFormatting = false
        }
    }
}