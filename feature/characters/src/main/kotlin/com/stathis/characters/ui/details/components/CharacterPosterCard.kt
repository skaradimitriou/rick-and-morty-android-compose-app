package com.stathis.characters.ui.details.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.stathis.common.util.DimenRes
import com.stathis.designsystem.components.images.CoilImage
import com.stathis.model.characters.CharacterResponse
import com.stathis.testing.CharactersFakes

/**
 * Composable used to display the [CharacterResponse] image along with
 * status & displayName
 */

@Composable
internal fun CharacterPosterCard(
    modifier: Modifier = Modifier,
    character: CharacterResponse,
    onCharacterClick: ((Int) -> Unit)? = null
) {
    Card(
        modifier = modifier.clickable(
            enabled = onCharacterClick != null,
            onClick = { onCharacterClick?.invoke(character.id) }
        )
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            CoilImage(
                modifier = Modifier.fillMaxSize(),
                imageUrlToLoad = character.image
            )
            CharacterLabel(
                modifier = Modifier.padding(all = dimensionResource(DimenRes.dimen_10)),
                characterDisplayName = character.characterDisplayLabel,
                characterStatus = character.status
            )
        }
    }
}

@Preview
@Composable
private fun CharacterPosterCardPreview() {
    CharacterPosterCard(
        character = CharactersFakes.provideDummyCharacter(),
        onCharacterClick = {}
    )
}
