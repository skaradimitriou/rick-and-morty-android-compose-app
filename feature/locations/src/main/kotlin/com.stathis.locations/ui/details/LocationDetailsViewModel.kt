package com.stathis.locations.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stathis.domain.usecases.locations.FetchLocationInfoByIdUseCase
import com.stathis.model.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

internal class LocationDetailsViewModel(
    private val locationId: Int,
    private val locationDetailsUseCase: FetchLocationInfoByIdUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _state: MutableStateFlow<LocationDetailsViewState> = MutableStateFlow(LocationDetailsViewState.Loading)
    val state = _state.asStateFlow()

    init {
        getData()
    }

    private fun getData() {
        locationDetailsUseCase.invoke(locationId)
            .onEach { result ->
                _state.update { result.toState() }
            }
            .flowOn(dispatcher)
            .launchIn(viewModelScope)
    }

    /**
     * Helper method to transform the [FetchLocationInfoByIdUseCase.LocationInformationResult]
     * to the appropriate [LocationDetailsViewState] state.
     */

    private fun Result<FetchLocationInfoByIdUseCase.LocationInformationResult>.toState() = when (this) {
        is Result.Loading -> LocationDetailsViewState.Loading
        is Result.Success -> LocationDetailsViewState.Content(
            location = data.locationInfo,
            residents = data.residents
        )

        is Result.Error -> LocationDetailsViewState.Error
    }
}
