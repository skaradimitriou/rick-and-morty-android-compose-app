package com.stathis.domain.usecases.locations

import com.stathis.domain.consts.UNSUPPORTED_ID
import com.stathis.domain.repository.CharactersRepository
import com.stathis.domain.repository.LocationRepository
import com.stathis.model.Result
import com.stathis.model.characters.CharacterResponse
import com.stathis.model.location.Location
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FetchLocationInfoByIdUseCase(
    private val locationRepository: LocationRepository,
    private val charactersRepository: CharactersRepository
) {

    operator fun invoke(locationId: Int): Flow<Result<LocationInformationResult>> = flow {
        if (locationId == UNSUPPORTED_ID) {
            error("Location id with zero value provided")
        }

        locationRepository.getLocationById(locationId).collect { result ->
            when (result) {
                is Result.Loading -> Unit

                is Result.Success -> {
                    charactersRepository.getMultipleCharacterById(
                        result.data.residents
                    ).collect { characterResult ->
                        when (characterResult) {
                            is Result.Loading -> Unit

                            is Result.Success -> {
                                emit(
                                    Result.Success(
                                        LocationInformationResult(
                                            locationInfo = result.data,
                                            residents = characterResult.data
                                        )
                                    )
                                )
                            }

                            is Result.Error -> emit(Result.Error(characterResult.exception))
                        }
                    }
                }

                is Result.Error -> emit(Result.Error(result.exception))
            }
        }
    }

    data class LocationInformationResult(
        var locationInfo: Location,
        var residents: List<CharacterResponse>
    )
}
