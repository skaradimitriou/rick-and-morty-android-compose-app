package com.stathis.locations.provider

import com.stathis.model.characters.CharacterResponse
import com.stathis.model.characters.CharacterStatus
import com.stathis.model.location.Location

/**
 * Simple way to get a [Location] for composable preview cases.
 */

internal val PREVIEW_LOCATION = Location(
    id = 3,
    name = "Citadel of Ricks",
    type = "Space station",
    dimension = "unknown",
    residents = listOf(),
    url = "https://rickandmortyapi.com/api/location/3",
    created = "2017-11-10T13:08:13.191Z"
)

/**
 * Simple way to get a list of  [CharacterResponse] for composable preview cases.
 */

internal val PREVIEW_RESIDENTS = listOf(
    CharacterResponse(
        id = 1,
        name = "Rick Sanchez",
        status = CharacterStatus.ALIVE,
        species = "Human",
        type = "XX",
        gender = "Male",
        origin = "Earth (C-137)",
        location = "Earth (Replacement Dimension)",
        image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
        episode = listOf("1", "2"),
        url = "https://rickandmortyapi.com/api/character/1",
        created = "2017-11-04T18:48:46.250Z",
    )
)
