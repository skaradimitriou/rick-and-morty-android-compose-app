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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stathis.common.util.DimenRes
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
            .height(80.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Detail(
            title = "Species",
            description = character?.species.toNotNull()
        )

        VerticalDivider(
            modifier = Modifier.padding(
                top = dimensionResource(DimenRes.dimen_10),
                bottom = dimensionResource(DimenRes.dimen_10)
            )
        )

        Detail(
            title = "Gender",
            description = character?.gender.toNotNull()
        )

        VerticalDivider(
            modifier = Modifier.padding(
                top = dimensionResource(DimenRes.dimen_10),
                bottom = dimensionResource(DimenRes.dimen_10)
            )
        )

        Detail(
            title = "Origin",
            description = character?.origin.toNotNull()
        )
    }
}

@Preview(showBackground = true)
@Composable
internal fun CharacterDetailsPreview() {
    val model = CharactersFakes.provideDummyCharacter()
    CharacterDetails(character = model)
}
