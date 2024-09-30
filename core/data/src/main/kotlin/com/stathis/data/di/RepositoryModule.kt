package com.stathis.data.di

import com.stathis.data.repository.CharactersRepository
import com.stathis.data.repository.CharactersRepositoryImpl
import com.stathis.data.repository.EpisodesRepository
import com.stathis.data.repository.EpisodesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideCharactersRepository(impl: CharactersRepositoryImpl): CharactersRepository

    @Binds
    abstract fun provideEpisodesRepository(impl: EpisodesRepositoryImpl): EpisodesRepository
}
