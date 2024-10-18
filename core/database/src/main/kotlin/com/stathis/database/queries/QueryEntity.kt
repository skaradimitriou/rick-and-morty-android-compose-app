package com.stathis.database.queries

import androidx.room.Entity
import androidx.room.PrimaryKey

const val QUERIES = "queries_table"

@Entity(tableName = QUERIES)
data class QueryEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String
)
