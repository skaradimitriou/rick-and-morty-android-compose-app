package com.stathis.database.db.characters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

internal class CharacterEpisodesConvertor {

    /**
     * Converts the List<String> to a Json.
     *
     * e.g input: [ "1","2","3" ] => output: "1, 2, 3"
     */

    @TypeConverter
    fun fromEpisodesToString(episodes: List<String>): String = Gson().toJson(episodes)

    /**
     * Converts the String containing the episodes of a single character back to a List<String>.
     *
     * e.g input: "1, 2, 3" => output: [ "1","2","3" ]
     */

    @TypeConverter
    fun fromStringToEpisodesList(data: String): List<String> =
        Gson().fromJson(data, object : TypeToken<List<String>>() {}.type)
}
