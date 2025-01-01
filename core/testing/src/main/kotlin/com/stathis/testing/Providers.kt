package com.stathis.testing

import com.stathis.model.characters.CharacterLocationInfo
import com.stathis.model.characters.CharacterResponse
import com.stathis.model.characters.CharacterStatus
import com.stathis.model.episodes.Episode
import com.stathis.model.location.Location

val DUMMY_CHARACTER = CharacterResponse(
    id = 1,
    name = "Rick Sanchez",
    status = CharacterStatus.ALIVE,
    species = "Human",
    type = "XX",
    gender = "Male",
    origin = CharacterLocationInfo(id = 1, name = "Earth (C-137)"),
    location = CharacterLocationInfo(id = 2, name = "Earth (Replacement Dimension)"),
    image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
    episode = listOf("1", "2"),
    url = "https://rickandmortyapi.com/api/character/1",
    created = "2017-11-04T18:48:46.250Z",
)

val DUMMY_EPISODE = Episode(
    id = 28,
    name = "The Ricklantis Mixup",
    airDate = "September 10, 2017",
    episode = "S03E07",
    characters = listOf(),
    url = "https://rickandmortyapi.com/api/episode/28",
    created = "2017-11-10T12:56:36.618Z"
)

val DUMMY_LOCATION = Location(
    id = 3,
    name = "Citadel of Ricks",
    type = "Space station",
    dimension = "unknown",
    residents = listOf(),
    url = "https://rickandmortyapi.com/api/location/3",
    created = "2017-11-10T13:08:13.191Z"
)
