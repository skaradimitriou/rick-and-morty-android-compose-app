package com.stathis.data.mapper.episodes

import com.stathis.model.episodes.Episode
import com.stathis.network.model.episodes.EpisodeDto
import org.junit.Test
import kotlin.test.assertEquals

class EpisodesMapperTest {

    @Test
    fun `given EpisodeDto dto input, when calling toDomainModel, return mapped non null domain model`() {
        val dtoModel: EpisodeDto = EpisodeDto(
            id = 123,
            name = "Some episode name",
            air_date = "XX-XX-2024",
            episode = "#123",
            characters = listOf(),
            url = "https://www.myepisode.com/123",
            created = "XX-XX-2024"
        )

        val domainModel: Episode = EpisodesMapper.toDomainModel(dtoModel)

        assertEquals(dtoModel.id, domainModel.id)
        assertEquals(dtoModel.name, domainModel.name)
        assertEquals(dtoModel.air_date, domainModel.airDate)
        assertEquals(dtoModel.episode, domainModel.episode)
        assertEquals(dtoModel.characters, domainModel.characters)
        assertEquals(dtoModel.url, domainModel.url)
        assertEquals(dtoModel.created, domainModel.created)
    }

    @Test
    fun `given null EpisodeDto dto input, when calling toDomainModel, return mapped non null domain model`() {
        val dtoModel: EpisodeDto? = null
        val domainModel: Episode = EpisodesMapper.toDomainModel(dtoModel)
        assertEquals(domainModel.id, 0)
        assertEquals(domainModel.name, "")
    }

    @Test
    fun `given EpisodeDto dto input with null fields, when calling toDomainModel, return mapped non null domain model`() {
        val dtoModel: EpisodeDto = EpisodeDto()
        val domainModel: Episode = EpisodesMapper.toDomainModel(dtoModel)
        assertEquals(domainModel.id, 0)
        assertEquals(domainModel.name, "")
    }
}
