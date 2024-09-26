package com.stathis.network.model.common

data class PaginationInfoDto(
    val count: Int? = null,
    val pages: Int? = null,
    val next: String? = null,
    val prev: Int? = null,
)