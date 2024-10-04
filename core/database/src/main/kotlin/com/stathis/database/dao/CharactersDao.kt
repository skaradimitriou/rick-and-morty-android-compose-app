package com.stathis.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.stathis.database.entities.CharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharactersDao {

    @Query("SELECT * FROM CHARACTERS")
    suspend fun getAllCharacters(): Flow<CharacterEntity>

    @Insert
    suspend fun insertAll(list: CharacterEntity)

    @Query("DELETE from CHARACTERS")
    suspend fun deleteAll()
}
