package com.dongnh.permissionsummary.ultil.exts

import android.annotation.SuppressLint

fun String.toNamePermission(): String {
    val index = lastIndexOf(".")
    if (index != -1) {
        return this.substring(index + 1, this.length)
    } else {
        return this
    }
}

@SuppressLint("DefaultLocale")
fun String.lowCaseViewName(): String? {
    val string = this.substring(0, 1).toUpperCase() + this.substring(1)
    return string
}

@SuppressLint("DefaultLocale")
fun String.lowCase(): String? {
    return this.replace("_", " ").toLowerCase()
}

@SuppressLint("DefaultLocale")
fun String.capitalizeWords(): String = split(" ").joinToString(" ") { it.capitalize() }