package com.stathis.network.model.characters

data class CharacterResponseDto(
    val id: Int? = null,
    val name: String? = null,
    val status: String? = null,
    val species: String? = null,
    val type: String? = null,
    val gender: String? = null,
    val image: String? = null,
    val url: String? = null,
    val created: String? = null
)