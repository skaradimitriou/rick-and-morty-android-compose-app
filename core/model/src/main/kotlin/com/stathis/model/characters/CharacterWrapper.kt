package com.stathis.model.characters

import com.stathis.model.common.PaginationInfo

data class CharacterWrapper(
    val info: PaginationInfo,
    val results: List<CharacterResponse>
)
