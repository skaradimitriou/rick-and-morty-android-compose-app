package com.stathis.data.mapper.common

import com.stathis.model.common.PaginationInfo
import com.stathis.network.model.common.PaginationInfoDto
import org.junit.Test
import kotlin.test.assertEquals

class PaginationMapperTest {

    @Test
    fun `given null PaginationInfoDto dto model, when calling toDomainModel, map to non null PaginationInfo domain model`() {
        val dtoModel: PaginationInfoDto? = null
        val domainModel = PaginationMapper.toDomainModel(dtoModel)
        val expected = PaginationInfo(0, 0, "", 0)
        assertEquals(domainModel, expected)
    }

    @Test
    fun `given PaginationInfoDto dto model, when calling toDomainModel, map to non null PaginationInfo domain model`() {
        val dtoModel = PaginationInfoDto(
            count = 1,
            pages = 10,
            next = "abc",
            prev = 0
        )

        val domainModel = PaginationMapper.toDomainModel(dtoModel)

        assertEquals(dtoModel.count, domainModel.count)
        assertEquals(dtoModel.pages, domainModel.pages)
        assertEquals(dtoModel.next, domainModel.next)
        assertEquals(dtoModel.prev, domainModel.prev)
    }
}
