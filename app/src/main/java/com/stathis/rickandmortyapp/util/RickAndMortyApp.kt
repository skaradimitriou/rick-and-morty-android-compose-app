package com.stathis.rickandmortyapp.util

import android.app.Application
import com.stathis.characters.di.charactersModule
import com.stathis.common.di.dispatchersModule
import com.stathis.data.di.dataModule
import com.stathis.domain.di.domainModule
import com.stathis.episodes.di.episodesModule
import com.stathis.locations.di.locationsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.module.Module

class RickAndMortyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val coreModules = listOf(
            dataModule,
            domainModule
        ).flatten()

        val featureModules: List<Module> = listOf(
            charactersModule,
            episodesModule,
            locationsModule
        )

        val modules = featureModules + coreModules + dispatchersModule

        startKoin {
            androidContext(this@RickAndMortyApp)
            modules(modules)
        }
    }
}
