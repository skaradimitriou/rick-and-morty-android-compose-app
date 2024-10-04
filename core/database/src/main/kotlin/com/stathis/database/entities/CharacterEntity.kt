package com.stathis.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.stathis.database.util.CHARACTERS_TABLE_NAME

@Entity(tableName = CHARACTERS_TABLE_NAME)
data class CharacterEntity(

    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: String,
    val location: String,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
)
