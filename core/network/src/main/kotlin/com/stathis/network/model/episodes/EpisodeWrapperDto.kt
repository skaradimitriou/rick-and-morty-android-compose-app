package com.stathis.network.model.episodes

import com.stathis.network.model.common.PaginationInfoDto

data class EpisodeWrapperDto(
    val info: PaginationInfoDto? = null,
    val results: List<EpisodeDto>? = null
)
