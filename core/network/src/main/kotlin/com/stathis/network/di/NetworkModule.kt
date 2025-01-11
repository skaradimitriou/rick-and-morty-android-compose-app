package com.stathis.network.di

import android.util.Log
import com.stathis.network.BuildConfig
import com.stathis.network.datasource.CharactersRemoteDataSource
import com.stathis.network.datasource.CharactersRemoteDataSourceImpl
import com.stathis.network.datasource.EpisodeRemoteDataSourceImpl
import com.stathis.network.datasource.EpisodesRemoteDataSource
import com.stathis.network.service.RickAndMortyApi
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
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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

}

//FIXME: Remove Retrofit
private val retrofitModule = module {
    single<Retrofit> { provideRetrofit() }
    single<RickAndMortyApi> { provideApi(retrofit = get()) }
}

val networkModule = httpModule + dataSourcesModule + retrofitModule

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

private fun provideRetrofit(): Retrofit {
    val logger = HttpLoggingInterceptor().also {
        if (BuildConfig.DEBUG) {
            /*
             * Log the content of the api calls ONLY in debug mode.
             */
            it.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    val client = OkHttpClient.Builder().addInterceptor(logger).build()

    return Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

private fun provideApi(retrofit: Retrofit): RickAndMortyApi = retrofit.create(RickAndMortyApi::class.java)
