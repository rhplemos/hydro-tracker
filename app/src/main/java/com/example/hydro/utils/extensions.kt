package com.example.hydro.utils

const val EMPTY_STRING = ""

fun Float?.toStringOrEmpty(): String {
    return if (this == 0f) {
        EMPTY_STRING
    } else {
        val value = this?.toInt()
        value.toString()
    }
}

fun Float?.formatToProgressIndicator(): Float {
    return this?.div(1000) ?: 0f
}