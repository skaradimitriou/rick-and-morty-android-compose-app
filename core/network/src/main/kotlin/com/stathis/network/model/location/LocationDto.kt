package com.stathis.network.model.location

data class LocationDto(
    val id: Int? = null,
    val name: Int? = null,
    val type: Int? = null,
    val dimension: Int? = null,
    val residents: List<String>? = null,
    val url: String? = null,
    val created: List<String>? = null,
)
