package com.stathis.database.db.queries

import androidx.room.Entity
import androidx.room.PrimaryKey

private const val QUERIES_TABLE_NAME = "queries_table"

@Entity(tableName = QUERIES_TABLE_NAME)
data class QueryEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String
)
