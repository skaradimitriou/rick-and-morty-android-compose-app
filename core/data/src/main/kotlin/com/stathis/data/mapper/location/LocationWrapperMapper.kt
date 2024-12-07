package com.stathis.data.mapper.location

import com.stathis.common.util.toListOf
import com.stathis.data.mapper.BaseMapper
import com.stathis.model.location.Location
import com.stathis.network.model.location.LocationWrapperDto

internal object LocationWrapperMapper : BaseMapper<LocationWrapperDto?, List<Location>> {

    override fun toDomainModel(dto: LocationWrapperDto?): List<Location> {
        return dto?.results.toListOf { item -> LocationMapper.toDomainModel(item) }
    }
}
