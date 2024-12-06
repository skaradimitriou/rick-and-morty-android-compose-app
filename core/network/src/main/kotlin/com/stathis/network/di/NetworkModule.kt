package com.stathis.network.di

import com.stathis.network.BuildConfig
import com.stathis.network.service.RickAndMortyApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single<Retrofit> { provideRetrofit() }
    single<RickAndMortyApi> { provideApi(retrofit = get()) }
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
