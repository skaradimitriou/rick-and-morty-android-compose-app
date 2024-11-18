package com.stathis.data.mapper.episodes

import com.stathis.model.episodes.Episode
import com.stathis.network.model.common.PaginationInfoDto
import com.stathis.network.model.episodes.EpisodeDto
import com.stathis.network.model.episodes.EpisodeWrapperDto
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class EpisodesWrapperMapperTest {

    @Test
    fun `given EpisodeWrapperDto dto input, when calling toDomainModel, return mapped non null domain model`() {
        val dtoModel: EpisodeWrapperDto = EpisodeWrapperDto(
            info = PaginationInfoDto(),
            results = listOf(
                EpisodeDto(
                    id = 123,
                    name = "Some episode name",
                    air_date = "XX-XX-2024",
                    episode = "#123",
                    characters = listOf(),
                    url = "https://www.myepisode.com/123",
                    created = "XX-XX-2024"
                )
            )
        )

        val domainModel: List<Episode> = EpisodesWrapperMapper.toDomainModel(dtoModel)

        dtoModel.results?.zip(domainModel) { dtoModel, domainModel ->
            assertEquals(dtoModel.id, domainModel.id)
            assertEquals(dtoModel.name, domainModel.name)
            assertEquals(dtoModel.air_date, domainModel.airDate)
            assertEquals(dtoModel.episode, domainModel.episode)
            assertEquals(dtoModel.characters, domainModel.characters)
            assertEquals(dtoModel.url, domainModel.url)
            assertEquals(dtoModel.created, domainModel.created)
        }
    }

    @Test
    fun `given null EpisodeWrapperDto dto input, when calling toDomainModel, return mapped non null domain model`() {
        val dtoModel: EpisodeWrapperDto? = null
        val domainModel: List<Episode> = EpisodesWrapperMapper.toDomainModel(dtoModel)
        assertTrue(domainModel.isEmpty())
    }

    @Test
    fun `given EpisodeWrapperDto input with null fields, when calling toDomainModel, return mapped non null domain model`() {
        val dtoModel: EpisodeWrapperDto = EpisodeWrapperDto()
        val domainModel: List<Episode> = EpisodesWrapperMapper.toDomainModel(dtoModel)
        assertTrue(domainModel.isEmpty())
    }
}
