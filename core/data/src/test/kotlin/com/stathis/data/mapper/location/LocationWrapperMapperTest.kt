package com.stathis.data.mapper.location

import com.stathis.model.location.Location
import com.stathis.network.model.common.PaginationInfoDto
import com.stathis.network.model.location.LocationDto
import com.stathis.network.model.location.LocationWrapperDto
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class LocationWrapperMapperTest {

    @Test
    fun `given LocationWrapperDto dto input, when calling toDomainModel, return mapped non null list of domain model`() {
        val dtoModel: LocationWrapperDto = LocationWrapperDto(
            info = PaginationInfoDto(),
            results = listOf(
                LocationDto(
                    id = 1,
                    name = "Earth",
                    type = "Location type",
                    dimension = "Dimension",
                    residents = listOf("abc/1", "abc/2", "abc/3"),
                    url = "my.awesome.url",
                    created = "XX-XX-2024"
                )
            )
        )

        val domainModel: List<Location> = LocationWrapperMapper.toDomainModel(dtoModel)

        dtoModel.results?.zip(domainModel) { dtoModel, domainModel ->
            assertEquals(dtoModel.id, domainModel.id)
            assertEquals(dtoModel.name, domainModel.name)
            assertEquals(dtoModel.type, domainModel.type)
            assertEquals(dtoModel.dimension, domainModel.dimension)
            assertEquals(dtoModel.residents?.map { it.substringAfterLast("/") }, domainModel.residents)
            assertEquals(dtoModel.url, domainModel.url)
            assertEquals(dtoModel.created, domainModel.created)
        }
    }

    @Test
    fun `given null LocationWrapperDto dto input, when calling toDomainModel, return mapped non null empty list of domain model`() {
        val dtoModel: LocationWrapperDto? = null
        val domainModel: List<Location> = LocationWrapperMapper.toDomainModel(dtoModel)
        assertTrue(domainModel.isEmpty())
    }

    @Test
    fun `given LocationWrapperDto dto input with null fields, when calling toDomainModel, return mapped non null list of domain model`() {
        val dtoModel: LocationWrapperDto = LocationWrapperDto()
        val domainModel: List<Location> = LocationWrapperMapper.toDomainModel(dtoModel)
        assertTrue(domainModel.isEmpty())
    }
}
