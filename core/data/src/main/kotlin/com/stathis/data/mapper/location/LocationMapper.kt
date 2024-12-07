package com.stathis.data.mapper.location

import com.stathis.common.util.toListOf
import com.stathis.common.util.toNotNull
import com.stathis.data.mapper.BaseMapper
import com.stathis.model.location.Location
import com.stathis.network.model.location.LocationDto

internal object LocationMapper : BaseMapper<LocationDto?, Location> {

    override fun toDomainModel(dto: LocationDto?): Location = Location(
        id = dto?.id.toNotNull(),
        name = dto?.name.toNotNull(),
        type = dto?.type.toNotNull(),
        dimension = dto?.dimension.toNotNull(),
        residents = dto?.residents.toListOf { it.substringAfterLast("/") },
        url = dto?.url.toNotNull(),
        created = dto?.created.toNotNull()
    )
}
