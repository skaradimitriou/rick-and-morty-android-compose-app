package com.stathis.testing

import com.stathis.model.characters.CharacterLocationInfo
import com.stathis.model.characters.CharacterResponse
import com.stathis.model.characters.CharacterStatus

object CharactersFakes {

    fun provideDummyCharacter(): CharacterResponse = CharacterResponse(
        id = 123,
        name = "Character Name",
        status = CharacterStatus.ALIVE,
        species = "Human",
        type = "Type",
        gender = "Male",
        origin = CharacterLocationInfo(id = 1, name = "Somewhere"),
        location = CharacterLocationInfo(id = 1, name = "Earth"),
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
