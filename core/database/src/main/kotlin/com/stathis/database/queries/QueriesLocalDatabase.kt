package com.stathis.database.queries

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [QueryEntity::class],
    version = 1
)
abstract class QueriesLocalDatabase : RoomDatabase() {

    abstract fun dao(): QueriesDao
}
