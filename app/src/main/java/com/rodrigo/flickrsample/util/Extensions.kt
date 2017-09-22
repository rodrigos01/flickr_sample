package com.rodrigo.flickrsample.util

import java.text.SimpleDateFormat
import java.util.*

fun Date.format(dateStyle: Int, timeStyle: Int): String {
    return SimpleDateFormat.getDateTimeInstance(dateStyle, timeStyle).format(this)
}
