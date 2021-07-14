package com.example.core.utils

import android.os.Build
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object TextConvert {
    fun String.changeIfEmpty(): String{
        return if (this == "") "-" else this
    }

    fun String.toDate(): String {
        if (this.isEmpty()) return this
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val format = DateTimeFormatter.ofPattern("MMM dd, yyyy")
            val date = LocalDate.parse(this)
            return date.format(format)
        }
        return this
    }
}