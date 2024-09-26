package com.stathis.data.mapper.common

import com.stathis.data.mapper.BaseMapper
import com.stathis.model.common.PaginationInfo
import com.stathis.network.model.common.PaginationInfoDto

object PaginationMapper : BaseMapper<PaginationInfoDto?, PaginationInfo> {

    override fun toDomainModel(dto: PaginationInfoDto?) = PaginationInfo(
        count = dto?.count ?: 0,
        pages = dto?.pages ?: 0,
        next = dto?.next ?: "",
        prev = dto?.prev ?: 0
    )
}