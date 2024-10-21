package com.stathis.model.location

data class Location(
    val id: Int,
    val name: Int,
    val type: Int,
    val dimension: Int,
    val residents: List<String>,
    val url: String,
    val created: List<String>,
)
