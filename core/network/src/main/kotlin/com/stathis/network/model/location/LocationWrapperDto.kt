package com.stathis.network.model.location

import com.stathis.network.model.common.PaginationInfoDto

data class LocationWrapperDto(
    val info: PaginationInfoDto? = null,
    val results: List<LocationDto>? = null
)
