package com.stathis.characters.di

import com.stathis.characters.ui.details.DetailsScreenViewModel
import com.stathis.characters.ui.home.HomeViewModel
import com.stathis.characters.ui.search.SearchScreenViewModel
import com.stathis.util.util.Dispatchers
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val charactersModule = module {

    viewModel {
        HomeViewModel(
            dispatcher = get<Dispatchers>().io(),
            useCase = get()
        )
    }

    viewModel { (characterId: Int) ->
        DetailsScreenViewModel(
            characterId = characterId,
            dispatcher = get<Dispatchers>().io(),
            useCase = get()
        )
    }

    viewModel {
        SearchScreenViewModel(
            dispatcher = get<Dispatchers>().io(),
            fetchAllUserQueriesUseCase = get(),
            saveUserQueryUseCase = get(),
            fetchQueryResultsUseCase = get()
        )
    }
}
