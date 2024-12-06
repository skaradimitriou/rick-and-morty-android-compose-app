package com.stathis.characters.di

import com.stathis.characters.ui.details.DetailsScreenViewModel
import com.stathis.characters.ui.home.HomeViewModel
import com.stathis.characters.ui.search.SearchScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val charactersModule = module {

    viewModel {
        HomeViewModel(
            dispatcher = get(named("IoDispatcher")),
            useCase = get()
        )
    }

    viewModel {
        DetailsScreenViewModel(
            dispatcher = get(named("IoDispatcher")),
            useCase = get()
        )
    }

    viewModel {
        SearchScreenViewModel(
            dispatcher = get(named("IoDispatcher")),
            fetchAllUserQueriesUseCase = get(),
            saveUserQueryUseCase = get(),
            fetchQueryResultsUseCase = get()
        )
    }
}
