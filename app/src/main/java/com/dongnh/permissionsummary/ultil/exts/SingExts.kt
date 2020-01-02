package com.dongnh.permissionsummary.ultil.exts

fun String.toNamePermission(): String {
    val index = lastIndexOf(".")
    if (index != -1) {
        return this.substring(index + 1, this.length)
    } else {
        return this
    }
}