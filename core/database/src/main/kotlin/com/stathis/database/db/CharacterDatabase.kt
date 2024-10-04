package com.stathis.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.stathis.database.dao.CharactersDao
import com.stathis.database.entities.CharacterEntity

@Database(
    entities = [CharacterEntity::class],
    version = 1
)
abstract class CharacterDatabase : RoomDatabase() {

    abstract fun dao(): CharactersDao
}
