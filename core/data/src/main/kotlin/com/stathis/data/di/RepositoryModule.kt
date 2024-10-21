package com.stathis.data.di

import com.stathis.data.repository.CharactersRepositoryImpl
import com.stathis.data.repository.EpisodesRepositoryImpl
import com.stathis.data.repository.LocationRepositoryImpl
import com.stathis.data.repository.QueriesRepositoryImpl
import com.stathis.database.characters.CharactersLocalDatabase
import com.stathis.database.queries.QueriesLocalDatabase
import com.stathis.domain.repository.CharactersRepository
import com.stathis.domain.repository.EpisodesRepository
import com.stathis.domain.repository.LocationRepository
import com.stathis.domain.repository.QueriesRepository
import com.stathis.network.service.RickAndMortyApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideCharactersRepository(
        api: RickAndMortyApi,
        localDb: CharactersLocalDatabase
    ): CharactersRepository = CharactersRepositoryImpl(
        remoteDataSource = api,
        localDataSource = localDb
    )

    @Provides
    @Singleton
    fun provideEpisodesRepository(
        api: RickAndMortyApi
    ): EpisodesRepository = EpisodesRepositoryImpl(remoteDataSource = api)

    @Provides
    @Singleton
    fun provideLocationRepository(
        api: RickAndMortyApi
    ): LocationRepository = LocationRepositoryImpl(remoteDataSource = api)

    @Provides
    @Singleton
    fun provideQueriesRepository(
        localDb: QueriesLocalDatabase
    ): QueriesRepository = QueriesRepositoryImpl(localDataSource = localDb)
}
