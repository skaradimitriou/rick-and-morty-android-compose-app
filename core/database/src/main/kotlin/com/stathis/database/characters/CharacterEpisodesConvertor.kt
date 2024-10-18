package com.stathis.database.characters

import androidx.room.TypeConverter

class CharacterEpisodesConvertor {

    /**
     * Converts the List<String> to a String.
     *
     * e.g input: [ "1","2","3" ] => output: "1, 2, 3"
     */

    @TypeConverter
    fun fromEpisodesToString(episodes: List<String>): String = episodes.toString()
        .removePrefix("[")
        .removeSuffix("]")

    /**
     * Converts the String containing the episodes of a single character back to a List<String>.
     *
     * e.g input: "1, 2, 3" => output: [ "1","2","3" ]
     */

    @TypeConverter
    fun fromStringToEpisodesList(data: String): List<String> = data.split(",")
}
