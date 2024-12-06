package com.stathis.episodes.di

import com.stathis.episodes.ui.details.EpisodeDetailsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val episodesModule = module {

    viewModel {
        EpisodeDetailsViewModel(
            dispatcher = get(named("IoDispatcher")),
            useCase = get()
        )
    }
}
