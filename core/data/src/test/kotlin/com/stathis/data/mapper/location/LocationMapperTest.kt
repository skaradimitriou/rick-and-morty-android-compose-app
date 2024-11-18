package com.stathis.data.mapper.location

import com.stathis.model.location.Location
import com.stathis.network.model.location.LocationDto
import kotlin.test.Test
import kotlin.test.assertEquals

class LocationMapperTest {

    @Test
    fun `given LocationDto dto input, when calling toDomainModel, return mapped non null Location domain model`() {
        val dtoModel: LocationDto = LocationDto(
            id = 1,
            name = "Earth",
            type = "Location type",
            dimension = "Dimension",
            residents = listOf("abc/1", "abc/2", "abc/3"),
            url = "my.awesome.url",
            created = "XX-XX-2024"
        )

        val domainModel: Location = LocationMapper.toDomainModel(dtoModel)

        assertEquals(dtoModel.id, domainModel.id)
        assertEquals(dtoModel.name, domainModel.name)
        assertEquals(dtoModel.type, domainModel.type)
        assertEquals(dtoModel.dimension, domainModel.dimension)
        assertEquals(dtoModel.residents?.map { it.substringAfterLast("/") }, domainModel.residents)
        assertEquals(dtoModel.url, domainModel.url)
        assertEquals(dtoModel.created, domainModel.created)
    }

    @Test
    fun `given null LocationDto dto input, when calling toDomainModel, return mapped non null Location domain model`() {
        val dtoModel: LocationDto? = null

        val domainModel: Location = LocationMapper.toDomainModel(dtoModel)

        assertEquals(domainModel.id, 0)
        assertEquals(domainModel.name, "")
        assertEquals(domainModel.type, "")
        assertEquals(domainModel.dimension, "")
        assertEquals(domainModel.residents.map { it.substringAfterLast("/") }, listOf())
        assertEquals(domainModel.url, "")
        assertEquals(domainModel.created, "")
    }

    @Test
    fun `given LocationDto dto input with null fields, when calling toDomainModel, return mapped non null Location domain model`() {
        val dtoModel: LocationDto = LocationDto()

        val domainModel: Location = LocationMapper.toDomainModel(dtoModel)

        assertEquals(domainModel.id, 0)
        assertEquals(domainModel.name, "")
        assertEquals(domainModel.type, "")
        assertEquals(domainModel.dimension, "")
        assertEquals(domainModel.residents.map { it.substringAfterLast("/") }, listOf())
        assertEquals(domainModel.url, "")
        assertEquals(domainModel.created, "")
    }
}
