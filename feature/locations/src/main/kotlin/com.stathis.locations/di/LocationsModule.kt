package com.stathis.locations.di

import com.stathis.locations.ui.details.LocationDetailsViewModel
import com.stathis.util.util.Dispatchers
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val locationsModule = module {

    viewModel { (locationId: Int) ->
        LocationDetailsViewModel(
            locationId = locationId,
            locationDetailsUseCase = get(),
            dispatcher = get<Dispatchers>().io()
        )
    }
}
