package com.stathis.database.di

import android.content.Context
import androidx.room.Room
import com.stathis.database.dao.CharactersDao
import com.stathis.database.db.CharacterDatabase
import com.stathis.database.util.DB_NAME
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
    ): CharacterDatabase = Room.databaseBuilder(
        context = context,
        klass = CharacterDatabase::class.java,
        name = DB_NAME
    ).build()

    @Provides
    @Singleton
    fun provideDao(db: CharacterDatabase): CharactersDao = db.dao()
}
