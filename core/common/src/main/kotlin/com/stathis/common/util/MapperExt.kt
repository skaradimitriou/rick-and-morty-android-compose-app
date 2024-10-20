package com.stathis.common.util

fun String?.toNotNull() = this ?: ""
fun Int?.toNotNull() = this ?: 0
fun Double?.toNotNull() = this ?: 0.0

fun <T> List<T>?.toNotNull() = this ?: listOf()

fun <OriginalModel, ResultModel> List<OriginalModel>?.toListOf(
    test: (OriginalModel) -> ResultModel
) = this?.map {
    test.invoke(it)
} ?: listOf()
