package com.stathis.data.di

import com.stathis.data.repository.CharactersRepositoryImpl
import com.stathis.data.repository.EpisodesRepositoryImpl
import com.stathis.data.repository.LocationRepositoryImpl
import com.stathis.data.repository.QueriesRepositoryImpl
import com.stathis.domain.repository.CharactersRepository
import com.stathis.domain.repository.EpisodesRepository
import com.stathis.domain.repository.LocationRepository
import com.stathis.domain.repository.QueriesRepository
import org.koin.dsl.module

val dataModule = module {

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
