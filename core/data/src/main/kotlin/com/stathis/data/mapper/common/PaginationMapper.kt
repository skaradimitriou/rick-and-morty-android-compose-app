package com.stathis.data.mapper.common

import com.stathis.common.util.toNotNull
import com.stathis.data.mapper.BaseMapper
import com.stathis.model.common.PaginationInfo
import com.stathis.network.model.common.PaginationInfoDto

object PaginationMapper : BaseMapper<PaginationInfoDto?, PaginationInfo> {

    override fun toDomainModel(dto: PaginationInfoDto?) = PaginationInfo(
        count = dto?.count.toNotNull(),
        pages = dto?.pages.toNotNull(),
        next = dto?.next.toNotNull(),
        prev = dto?.prev.toNotNull()
    )
}