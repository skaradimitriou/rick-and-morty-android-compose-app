package com.stathis.data.mapper

interface BaseMapper<DtoModel, DomainModel> {

    fun toDomainModel(dto: DtoModel): DomainModel
}