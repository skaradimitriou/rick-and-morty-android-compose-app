package com.stathis.network.model.characters

import com.stathis.network.model.common.PaginationInfoDto

data class CharacterWrapperDto(
    val info: PaginationInfoDto? = null,
    val results: List<CharacterResponseDto>? = null
)