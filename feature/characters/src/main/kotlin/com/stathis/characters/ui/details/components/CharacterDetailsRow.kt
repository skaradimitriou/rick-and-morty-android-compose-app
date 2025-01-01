package com.stathis.characters.ui.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.stathis.model.location.Location
import com.stathis.testing.DUMMY_LOCATION
import com.stathis.util.util.DimenRes
import com.stathis.util.util.StringRes

@Composable
internal fun CharacterDetailsRow(
    modifier: Modifier = Modifier,
    species: String,
    gender: String,
    origin: Location,
    currentLocation: Location
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Detail(
                modifier = Modifier.weight(1f),
                title = stringResource(StringRes.species),
                description = species
            )
            Spacer(modifier = Modifier.width(dimensionResource(DimenRes.dimen_10)))
            Detail(
                modifier = Modifier.weight(1f),
                title = stringResource(StringRes.gender),
                description = gender
            )
        }
        Spacer(modifier = Modifier.height(dimensionResource(DimenRes.dimen_10)))
        CharacterLocation(
            title = stringResource(StringRes.origin),
            locationName = origin.name,
            locationType = origin.type,
            dimension = origin.dimension
        )
        Spacer(modifier = Modifier.height(dimensionResource(DimenRes.dimen_10)))
        CharacterLocation(
            title = stringResource(StringRes.location),
            locationName = currentLocation.name,
            locationType = currentLocation.type,
            dimension = currentLocation.dimension
        )
    }
}

@Preview
@Composable
private fun CharacterDetailsRowPreview() {
    CharacterDetailsRow(
        species = "Human",
        gender = "Male",
        origin = DUMMY_LOCATION,
        currentLocation = DUMMY_LOCATION
    )
}
