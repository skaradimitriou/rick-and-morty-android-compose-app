package com.stathis.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.stathis.database.entities.CharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharactersDao {

    @Query("SELECT * FROM CHARACTERS")
    fun getAllCharacters(): Flow<List<CharacterEntity>>

    @Query("SELECT * FROM CHARACTERS where id=:id")
    fun getCharacterById(id: Int): Flow<CharacterEntity?>

    @Insert
    suspend fun insertAll(list: List<CharacterEntity>)

    @Query("DELETE from CHARACTERS")
    suspend fun deleteAll()
}
