package com.stathis.locations.ui.details

import com.stathis.model.characters.CharacterResponse
import com.stathis.model.location.Location

/**
 * Represents the ViewState inside the Location Details Screen
 */

sealed class LocationDetailsViewState {

    /**
     * Represents the initial loading state of the screen.
     */

    data object Loading : LocationDetailsViewState()

    /**
     * Represents the content state that holds information about:
     * @param location: the [Location] that will be bound to UI.
     * @param residents: List of [CharacterResponse] who resides in that [Location].
     */

    data class Content(
        val location: Location,
        val residents: List<CharacterResponse>
    ) : LocationDetailsViewState()

    /**
     * Represents the error state of the screen.
     */

    data object Error : LocationDetailsViewState()
}
