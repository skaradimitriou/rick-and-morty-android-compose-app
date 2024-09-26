package com.stathis.data.di

import com.stathis.data.repository.CharactersRepository
import com.stathis.data.repository.CharactersRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideCharactersRepository(impl: CharactersRepositoryImpl): CharactersRepository
}