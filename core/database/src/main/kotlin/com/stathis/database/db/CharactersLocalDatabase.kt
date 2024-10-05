package com.stathis.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.stathis.database.converters.CharacterEpisodesConvertor
import com.stathis.database.dao.CharactersDao
import com.stathis.database.entities.CharacterEntity

@Database(
    entities = [CharacterEntity::class],
    version = 1
)
@TypeConverters(CharacterEpisodesConvertor::class)
abstract class CharactersLocalDatabase : RoomDatabase() {

    abstract fun dao(): CharactersDao
}
