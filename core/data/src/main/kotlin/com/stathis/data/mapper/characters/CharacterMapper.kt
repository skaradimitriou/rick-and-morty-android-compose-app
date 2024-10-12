package com.stathis.data.mapper.characters

import com.stathis.data.mapper.BaseMapper
import com.stathis.data.mapper.common.PaginationMapper
import com.stathis.model.characters.CharacterWrapper
import com.stathis.network.model.characters.CharacterWrapperDto

object CharacterMapper : BaseMapper<CharacterWrapperDto?, CharacterWrapper> {

    override fun toDomainModel(dto: CharacterWrapperDto?): CharacterWrapper = CharacterWrapper(
        info = PaginationMapper.toDomainModel(dto?.info),
        results = CharacterListMapper.toDomainModel(dto?.results)
    )
}
