package com.stathis.characters.ui.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.stathis.util.util.DimenRes
import com.stathis.util.util.StringRes

@Composable
fun CharacterDetailsRow(
    modifier: Modifier = Modifier,
    species: String,
    gender: String,
    origin: String
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(DimenRes.dimen_10))
    ) {
        Detail(
            modifier = Modifier.weight(1f),
            title = stringResource(StringRes.species),
            description = species
        )

        Detail(
            modifier = Modifier.weight(1f),
            title = stringResource(StringRes.gender),
            description = gender
        )

        Detail(
            modifier = Modifier.weight(1f),
            title = stringResource(StringRes.origin),
            description = origin
        )
    }
}

@Preview
@Composable
fun CharacterDetailsRowPreview() {
    CharacterDetailsRow(
        species = "Human",
        gender = "Male",
        origin = "Earth"
    )
}
