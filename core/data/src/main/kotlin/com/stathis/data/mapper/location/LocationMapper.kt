package com.stathis.data.mapper.location

import com.stathis.data.mapper.BaseMapper
import com.stathis.model.location.Location
import com.stathis.network.model.location.LocationDto
import com.stathis.util.util.toListOf
import com.stathis.util.util.toNotNull

internal object LocationMapper : BaseMapper<LocationDto?, Location> {

    override fun toDomainModel(dto: LocationDto?): Location = Location(
        id = dto?.id.toNotNull(),
        name = dto?.name.toNotNull().ifEmpty { "N/A" },
        type = dto?.type.toNotNull().ifEmpty { "N/A" },
        dimension = dto?.dimension.toNotNull().ifEmpty { "N/A" },
        residents = dto?.residents.toListOf { it.substringAfterLast("/") },
        url = dto?.url.toNotNull(),
        created = dto?.created.toNotNull()
    )
}
