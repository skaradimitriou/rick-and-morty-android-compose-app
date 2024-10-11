package com.stathis.characters.navigation.model

import kotlinx.serialization.Serializable

@Serializable
data class CharacterDetailsArgs(
    val characterId: Int
)
