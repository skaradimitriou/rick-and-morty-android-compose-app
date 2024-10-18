package com.stathis.database.di

import android.content.Context
import androidx.room.Room
import com.stathis.database.characters.CharactersDao
import com.stathis.database.characters.CharactersLocalDatabase
import com.stathis.database.queries.QueriesDao
import com.stathis.database.queries.QueriesLocalDatabase
import com.stathis.database.util.DB_NAME
import com.stathis.database.util.DB_QUERIES_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): CharactersLocalDatabase = Room.databaseBuilder(
        context = context,
        klass = CharactersLocalDatabase::class.java,
        name = DB_NAME
    ).build()

    @Provides
    @Singleton
    fun provideDao(db: CharactersLocalDatabase): CharactersDao = db.dao()

    @Provides
    @Singleton
    fun provideQueriesDatabase(
        @ApplicationContext context: Context
    ): QueriesLocalDatabase = Room.databaseBuilder(
        context = context,
        klass = QueriesLocalDatabase::class.java,
        name = DB_QUERIES_NAME
    ).build()

    @Provides
    @Singleton
    fun provideQueriesDao(db: QueriesLocalDatabase): QueriesDao = db.dao()
}
