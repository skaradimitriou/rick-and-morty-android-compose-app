package com.stathis.network.di

import android.util.Log
import com.stathis.network.datasource.CharactersRemoteDataSource
import com.stathis.network.datasource.CharactersRemoteDataSourceImpl
import com.stathis.network.datasource.EpisodeRemoteDataSourceImpl
import com.stathis.network.datasource.EpisodesRemoteDataSource
import com.stathis.network.datasource.LocationsRemoteDataSource
import com.stathis.network.datasource.LocationsRemoteDataSourceImpl
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.header
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.gson.gson
import org.koin.dsl.module

private const val TIME_OUT = 6000

private val httpModule = module {
    single<HttpClient> { provideHttpClient() }
}

private val dataSourcesModule = module {
    /**
     * Remote DataSources exposed to the :core:data layer
     */
    single<CharactersRemoteDataSource> { CharactersRemoteDataSourceImpl(client = get()) }
    single<EpisodesRemoteDataSource> { EpisodeRemoteDataSourceImpl(client = get()) }
    single<LocationsRemoteDataSource> { LocationsRemoteDataSourceImpl(client = get()) }

}

val networkModule = httpModule + dataSourcesModule

private fun provideHttpClient(): HttpClient {
    val client = HttpClient(Android) {
        install(ContentNegotiation) {
            gson {
                setPrettyPrinting()
            }
        }

        engine {
            connectTimeout = TIME_OUT
            socketTimeout = TIME_OUT
        }

        //Logging
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Log.d("HttpLogging:", message)
                }
            }
        }

        //Http Response
        install(ResponseObserver) {
            onResponse { response ->
                Log.d("<------ HTTP status:", "${response.status.value}")
                Log.d("Response:", response.bodyAsText())
            }
        }

        // Headers
        install(DefaultRequest) {
            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }
    }

    return client
}
