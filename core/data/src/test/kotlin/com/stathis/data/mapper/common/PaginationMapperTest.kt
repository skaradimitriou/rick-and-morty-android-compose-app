package com.stathis.data.mapper.common

import com.stathis.model.common.PaginationInfo
import com.stathis.network.model.common.PaginationInfoDto
import org.junit.Test
import kotlin.test.assertEquals

class PaginationMapperTest {

    @Test
    fun `mapping of PaginationInfoDto to empty PaginationInfo model on null input`() {
        val dtoModel: PaginationInfoDto? = null
        val domainModel = PaginationMapper.toDomainModel(dtoModel)
        val expected = PaginationInfo(0, 0, "", 0)
        assertEquals(domainModel, expected)
    }

    @Test
    fun `mapping of PaginationInfoDto to PaginationInfo model on null input`() {
        val dtoModel = PaginationInfoDto(123, 234, "abc", 345)
        val domainModel = PaginationMapper.toDomainModel(dtoModel)
        val expected = PaginationInfo(123, 234, "abc", 345)
        assertEquals(domainModel, expected)
    }
}
