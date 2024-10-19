package com.stathis.database.queries

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface QueriesDao {

    @Query("SELECT * FROM queries_table ORDER BY id DESC")
    fun getAllQueries(): Flow<List<QueryEntity>>

    @Query("SELECT * FROM queries_table WHERE name = :name")
    fun getQueryByName(name: String): Flow<List<QueryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(query: QueryEntity)
}
