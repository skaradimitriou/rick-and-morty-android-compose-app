package com.stathis.model.characters

data class CharacterResponse(
    val id: Int,
    val name: String,
    val status: CharacterStatus,
    val species: String,
    val type: String,
    val gender: String,
    val origin: CharacterLocationInfo,
    val location: CharacterLocationInfo,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
) {

    /**
     * Used to display the character id along with character name
     */
    val characterDisplayLabel: String = "#$id $name"
}

data class CharacterLocationInfo(
    val name: String,
    val id: Int
)

enum class CharacterStatus {
    ALIVE, DEAD, UNKNOWN
}
