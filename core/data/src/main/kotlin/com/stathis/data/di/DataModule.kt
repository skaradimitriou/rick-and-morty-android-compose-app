package com.stathis.data.di

import com.stathis.data.repository.CharactersRepositoryImpl
import com.stathis.data.repository.EpisodesRepositoryImpl
import com.stathis.data.repository.LocationRepositoryImpl
import com.stathis.data.repository.QueriesRepositoryImpl
import com.stathis.database.di.databaseModule
import com.stathis.domain.repository.CharactersRepository
import com.stathis.domain.repository.EpisodesRepository
import com.stathis.domain.repository.LocationRepository
import com.stathis.domain.repository.QueriesRepository
import com.stathis.network.di.networkModule
import org.koin.dsl.module

private val repositoryModule = module {

    single<CharactersRepository> {
        CharactersRepositoryImpl(
            remoteDataSource = get(),
            localDataSource = get()
        )
    }

    single<EpisodesRepository> {
        EpisodesRepositoryImpl(remoteDataSource = get())
    }

    single<LocationRepository> {
        LocationRepositoryImpl(remoteDataSource = get())
    }

    single<QueriesRepository> {
        QueriesRepositoryImpl(localDataSource = get())
    }
}

val dataModule = repositoryModule + networkModule + databaseModule
