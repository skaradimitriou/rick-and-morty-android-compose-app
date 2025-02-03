package com.stathis.episodes.di

import com.stathis.episodes.ui.details.EpisodeDetailsViewModel
import com.stathis.util.util.Dispatchers
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val episodesModule = module {

    viewModel { (episodeId: Int) ->
        EpisodeDetailsViewModel(
            episodeId = episodeId,
            dispatcher = get<Dispatchers>().io(),
            useCase = get()
        )
    }
}
