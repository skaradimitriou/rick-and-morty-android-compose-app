package com.stathis.data.mapper.episodes

import com.stathis.network.model.episodes.EpisodeDto
import com.stathis.testing.EpisodeFakes
import org.junit.Test
import kotlin.test.assertEquals

class EpisodesMapperTest {

    @Test
    fun `mapping of EpisodeDto to empty Episode model on null input`() {
        val dtoModel: EpisodeDto? = null
        val domainModel = EpisodesMapper.toDomainModel(dtoModel)
        val expected = EpisodeFakes.provideEmptyEpisode()
        assertEquals(domainModel, expected)
    }

    @Test
    fun `mapping of EpisodeDto to empty Episode model on non-null input`() {
        val dtoModel = EpisodeDto(
            id = 123,
            name = "Some episode name",
            air_date = "XX-XX-2024",
            episode = "#123",
            characters = listOf(),
            url = "https://www.myepisode.com/123",
            created = "XX-XX-2024"
        )
        val domainModel = EpisodesMapper.toDomainModel(dtoModel)
        val expected = EpisodeFakes.provideDummyEpisode()
        assertEquals(domainModel, expected)
    }
}
