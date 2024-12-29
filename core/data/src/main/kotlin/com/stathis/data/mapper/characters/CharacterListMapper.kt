package com.stathis.data.mapper.characters

import com.stathis.data.mapper.BaseMapper
import com.stathis.model.characters.CharacterResponse
import com.stathis.network.model.characters.CharacterResponseDto
import com.stathis.util.util.toListOf

internal object CharacterListMapper : BaseMapper<List<CharacterResponseDto>?, List<CharacterResponse>> {

    override fun toDomainModel(dto: List<CharacterResponseDto>?) = dto.toListOf {
        CharacterResponseMapper.toDomainModel(it)
    }
}
