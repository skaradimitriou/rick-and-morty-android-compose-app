package com.stathis.model.common

data class PaginationInfo(
    val count: Int,
    val pages: Int,
    val next: String,
    val prev: Int
)
