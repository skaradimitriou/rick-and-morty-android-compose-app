package com.stathis.domain.di

import com.stathis.domain.usecases.characters.FetchAllCharactersUseCase
import com.stathis.domain.usecases.characters.FetchCharacterDetailsUseCase
import com.stathis.domain.usecases.episodes.FetchEpisodeDetailsUseCase
import com.stathis.domain.usecases.episodes.FetchEpisodesByIdUseCase
import com.stathis.domain.usecases.search.FetchAllUserQueriesUseCase
import com.stathis.domain.usecases.search.FetchQueryResultsUseCase
import com.stathis.domain.usecases.search.SaveQueryToLocalDbUseCase
import org.koin.dsl.module

val charactersDomainModule = module {
    single<FetchAllCharactersUseCase> {
        FetchAllCharactersUseCase(repository = get())
    }

    single<FetchCharacterDetailsUseCase> {
        FetchCharacterDetailsUseCase(
            charactersRepository = get(),
            episodesRepository = get()
        )
    }
}

val episodesDomainModule = module {
    single<FetchEpisodeDetailsUseCase> {
        FetchEpisodeDetailsUseCase(
            charactersRepository = get(),
            episodesRepository = get()
        )
    }

    single<FetchEpisodesByIdUseCase> {
        FetchEpisodesByIdUseCase(
            repository = get()
        )
    }
}

val searchDomainModule = module {
    single<FetchAllUserQueriesUseCase> {
        FetchAllUserQueriesUseCase(
            repo = get()
        )
    }

    single<FetchQueryResultsUseCase> {
        FetchQueryResultsUseCase(
            charactersRepository = get(),
            episodesRepository = get(),
            locationRepository = get()
        )
    }

    single<SaveQueryToLocalDbUseCase> {
        SaveQueryToLocalDbUseCase(
            repo = get()
        )
    }
}
