package com.stathis.database.util

import com.stathis.database.characters.CharacterEntity
import com.stathis.model.characters.CharacterResponse

/**
 * Maps a [CharacterEntity] to a [CharacterResponse].
 */

fun CharacterEntity.toCharacter(): CharacterResponse = CharacterResponse(
    id = this.id,
    name = this.name,
    status = this.status,
    species = this.species,
    type = this.type,
    gender = this.gender,
    origin = this.origin,
    location = this.location,
    image = this.image,
    episode = this.episode,
    url = this.url,
    created = this.created
)

/**
 * Maps a [CharacterResponse] back to a [CharacterEntity].
 */

fun CharacterResponse.toEntity(): CharacterEntity = CharacterEntity(
    id = this.id,
    name = this.name,
    status = this.status,
    species = this.species,
    type = this.type,
    gender = this.gender,
    origin = this.origin,
    location = this.location,
    image = this.image,
    episode = this.episode,
    url = this.url,
    created = this.created
)
