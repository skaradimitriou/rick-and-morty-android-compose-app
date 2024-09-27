package com.stathis.common.util

fun String?.toNotNull() = this ?: ""
fun Int?.toNotNull() = this ?: 0
fun Double?.toNotNull() = this ?: 0.0