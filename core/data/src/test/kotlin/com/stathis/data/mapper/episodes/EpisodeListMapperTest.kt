package com.stathis.data.mapper.episodes

import com.stathis.model.episodes.Episode
import com.stathis.network.model.episodes.EpisodeDto
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class EpisodeListMapperTest {

    @Test
    fun `given dto model list as input, when calling toDomainModel method, return mapped list of domain models`() {
        val dtoModels: List<EpisodeDto> = listOf(
            EpisodeDto(
                id = 1,
                name = "My episode name"
            )
        )

        val domainModels: List<Episode> = EpisodeListMapper.toDomainModel(dtoModels)

        assertTrue(domainModels.isNotEmpty())

        dtoModels.zip(domainModels) { dtoModel, domainModel ->
            assertEquals(dtoModel.id, domainModel.id)
            assertEquals(dtoModel.name, domainModel.name)
        }
    }

    @Test
    fun `given dto list with item with null values, when calling toDomainModel method, return mapped list of domain models`() {
        val dtoModels: List<EpisodeDto> = listOf(EpisodeDto())
        val domainModels: List<Episode> = EpisodeListMapper.toDomainModel(dtoModels)

        assertTrue(domainModels.isNotEmpty())
        assertEquals(domainModels.first().id, 0)
        assertEquals(domainModels.first().name, "")
    }

    @Test
    fun `given null dto model list as input, when calling toDomainModel method, return mapped list of domain models`() {
        val dtoModels: List<EpisodeDto>? = null
        val domainModels: List<Episode> = EpisodeListMapper.toDomainModel(dtoModels)
        assertTrue(domainModels.isEmpty())
    }
}
