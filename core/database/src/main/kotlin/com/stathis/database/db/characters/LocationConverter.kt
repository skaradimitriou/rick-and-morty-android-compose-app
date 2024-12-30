package com.stathis.database.db.characters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.stathis.model.characters.CharacterLocationInfo

object LocationConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromModelToJson(model: CharacterLocationInfo): String {
        return gson.toJson(model)
    }

    @TypeConverter
    fun fromJsonToModel(source: String): CharacterLocationInfo {
        return gson.fromJson(source, CharacterLocationInfo::class.java)
    }
}
