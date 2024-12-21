package com.stathis.characters.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.stathis.common.util.DimenRes
import com.stathis.common.util.StringRes
import com.stathis.designsystem.components.images.CoilImage
import com.stathis.model.characters.CharacterResponse
import com.stathis.testing.CharactersFakes

private const val SIXTY_PERCENT = 0.6f

@Composable
internal fun CharacterCard(
    character: CharacterResponse,
    onCharacterClick: ((Int) -> Unit)? = null
) {
    Card(
        modifier = Modifier
            .height(dimensionResource(DimenRes.dimen_200))
            .width(dimensionResource(DimenRes.dimen_80))
            .clickable { onCharacterClick?.invoke(character.id) }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            CoilImage(
                modifier = Modifier.fillMaxSize(),
                imageUrlToLoad = character.image
            )
            CharacterLabel(
                text = stringResource(StringRes.character_label, character.id, character.name)
            )
        }
    }
}

@Composable
private fun BoxScope.CharacterLabel(text: String) {
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .padding(all = dimensionResource(DimenRes.dimen_8))
            .clip(shape = RoundedCornerShape(dimensionResource(DimenRes.dimen_8)))
            .background(Color.Black.copy(alpha = SIXTY_PERCENT))
            .align(alignment = Alignment.BottomCenter),
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = dimensionResource(DimenRes.dimen_4)),
            textAlign = TextAlign.Center,
            text = text,
            color = Color.White
        )
    }
}

@Preview
@Composable
fun CharacterCardPreview() {
    CharacterCard(
        character = CharactersFakes.provideDummyCharacter(),
        onCharacterClick = {}
    )
}
