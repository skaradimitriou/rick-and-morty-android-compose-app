package com.stathis.database.characters

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [CharacterEntity::class],
    version = 1
)
@TypeConverters(CharacterEpisodesConvertor::class)
abstract class CharactersLocalDatabase : RoomDatabase() {

    abstract fun dao(): CharactersDao
}
