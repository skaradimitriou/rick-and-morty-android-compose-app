package com.stathis.rickandmortyapp.util

import android.app.Application
import com.stathis.characters.di.charactersModule
import com.stathis.common.di.dispatchersModule
import com.stathis.data.di.dataModule
import com.stathis.database.di.databaseModule
import com.stathis.domain.di.charactersDomainModule
import com.stathis.domain.di.episodesDomainModule
import com.stathis.domain.di.searchDomainModule
import com.stathis.episodes.di.episodesModule
import com.stathis.network.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.module.Module

class RickAndMortyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val coreModule = listOf(
            networkModule,
            databaseModule,
            dataModule,

            charactersDomainModule,
            episodesDomainModule,
            searchDomainModule,

            dispatchersModule
        )

        val featureModules: List<Module> = listOf(
            charactersModule,
            episodesModule
        )

        val modules = featureModules + coreModule

        startKoin {
            androidContext(this@RickAndMortyApp)
            modules(modules)
        }
    }
}
