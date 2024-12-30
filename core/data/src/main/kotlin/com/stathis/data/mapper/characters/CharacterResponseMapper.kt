package com.stathis.data.mapper.characters

import com.stathis.data.mapper.BaseMapper
import com.stathis.model.characters.CharacterLocationInfo
import com.stathis.model.characters.CharacterResponse
import com.stathis.model.characters.CharacterStatus
import com.stathis.network.model.characters.CharacterInformationDto
import com.stathis.network.model.characters.CharacterResponseDto
import com.stathis.util.util.toListOf
import com.stathis.util.util.toNotNull

internal object CharacterResponseMapper : BaseMapper<CharacterResponseDto?, CharacterResponse> {

    override fun toDomainModel(dto: CharacterResponseDto?) = CharacterResponse(
        id = dto?.id.toNotNull(),
        name = dto?.name.toNotNull(),
        status = CharacterStatus.valueOf(dto?.status?.uppercase() ?: CharacterStatus.UNKNOWN.name),
        species = dto?.species.toNotNull(),
        type = dto?.type.toNotNull(),
        gender = dto?.gender.toNotNull(),
        origin = dto?.origin.toDomainModel(),
        location = dto?.location.toDomainModel(),
        image = dto?.image.toNotNull(),
        episode = dto?.episode.toDomainEpisodeIds(),
        url = dto?.url.toNotNull(),
        created = dto?.created.toNotNull()
    )

    private fun CharacterInformationDto?.toDomainModel(): CharacterLocationInfo {
        val id = when (val value = this?.url?.substringAfterLast("/").toNotNull()) {
            "" -> 0
            else -> value.toInt()
        }

        return CharacterLocationInfo(
            id = id,
            name = this?.name.toNotNull()
        )
    }

    private fun List<String>?.toDomainEpisodeIds() = toListOf { it.substringAfterLast("/") }
}
