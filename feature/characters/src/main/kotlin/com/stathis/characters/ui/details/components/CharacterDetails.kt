package com.stathis.characters.ui.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.stathis.common.util.DimenRes
import com.stathis.common.util.StringRes
import com.stathis.common.util.toNotNull
import com.stathis.model.characters.CharacterResponse
import com.stathis.testing.CharactersFakes

@Composable
internal fun CharacterDetails(
    modifier: Modifier = Modifier,
    character: CharacterResponse?
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(dimensionResource(DimenRes.dimen_80)),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Detail(
            modifier = Modifier.weight(1f),
            title = stringResource(StringRes.species),
            description = character?.species.toNotNull()
        )

        VerticalDivider(
            modifier = Modifier.padding(
                top = dimensionResource(DimenRes.dimen_10),
                bottom = dimensionResource(DimenRes.dimen_10)
            )
        )

        Detail(
            modifier = Modifier.weight(1f),
            title = stringResource(StringRes.gender),
            description = character?.gender.toNotNull()
        )

        VerticalDivider(
            modifier = Modifier.padding(
                top = dimensionResource(DimenRes.dimen_10),
                bottom = dimensionResource(DimenRes.dimen_10)
            )
        )

        Detail(
            modifier = Modifier.weight(1f),
            title = stringResource(StringRes.origin),
            description = character?.origin.toNotNull()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CharacterDetailsPreview() {
    val model = CharactersFakes.provideDummyCharacter()
    CharacterDetails(character = model)
}
