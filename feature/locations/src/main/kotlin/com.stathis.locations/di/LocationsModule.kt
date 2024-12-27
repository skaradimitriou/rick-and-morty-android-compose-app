package com.stathis.locations.di

import com.stathis.locations.ui.details.LocationDetailsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val locationsModule = module {

    viewModel { (locationId: Int) ->
        LocationDetailsViewModel(
            locationId = locationId,
            locationDetailsUseCase = get(),
            dispatcher = get(named("IoDispatcher"))
        )
    }
}
