package com.stathis.testing

import com.stathis.model.characters.CharacterResponse
import com.stathis.model.characters.CharacterStatus

object CharactersFakes {

    fun provideEmptyCharacter(): CharacterResponse = CharacterResponse(
        id = 0,
        name = "",
        status = CharacterStatus.UNKNOWN,
        species = "",
        type = "",
        gender = "",
        origin = "",
        location = "",
        image = "",
        episode = listOf(),
        url = "",
        created = ""
    )

    fun provideDummyCharacter(): CharacterResponse = CharacterResponse(
        id = 123,
        name = "Character Name",
        status = CharacterStatus.ALIVE,
        species = "Human",
        type = "Type",
        gender = "Male",
        origin = "Somewhere",
        location = "Earth",
        image = "",
        episode = listOf("8"),
        url = "",
        created = ""
    )

    fun provideDummyCharacterList(): List<CharacterResponse> = listOf(
        provideDummyCharacter(),
        provideDummyCharacter(),
        provideDummyCharacter(),
        provideDummyCharacter()
    )
}
