package com.stathis.database.di

import android.app.Application
import androidx.room.Room
import com.stathis.database.db.characters.CharactersDao
import com.stathis.database.db.characters.CharactersLocalDatabase
import com.stathis.database.db.queries.QueriesDao
import com.stathis.database.db.queries.QueriesLocalDatabase
import com.stathis.database.util.DB_NAME
import com.stathis.database.util.DB_QUERIES_NAME
import org.koin.dsl.module

val databaseModule = module {

    /*
     * Characters Local Database
     */
    single<CharactersLocalDatabase> { provideCharactersDatabase(application = get()) }
    single<CharactersDao> { provideCharactersDao(db = get()) }

    /*
     * Queries Local Database
     */
    single<QueriesLocalDatabase> { provideQueriesDatabase(application = get()) }
    single<QueriesDao> { provideQueriesDao(db = get()) }
}

internal fun provideCharactersDatabase(application: Application): CharactersLocalDatabase = Room.databaseBuilder(
    context = application,
    klass = CharactersLocalDatabase::class.java,
    name = DB_NAME
).fallbackToDestructiveMigration()
    .build()

internal fun provideCharactersDao(db: CharactersLocalDatabase) = db.dao()

internal fun provideQueriesDatabase(application: Application) = Room.databaseBuilder(
    context = application,
    klass = QueriesLocalDatabase::class.java,
    name = DB_QUERIES_NAME
).fallbackToDestructiveMigration().build()

internal fun provideQueriesDao(db: QueriesLocalDatabase) = db.dao()
