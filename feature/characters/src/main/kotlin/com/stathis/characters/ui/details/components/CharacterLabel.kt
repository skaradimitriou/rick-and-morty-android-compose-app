package com.stathis.characters.ui.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.stathis.characters.ui.details.providers.CharacterStatusPreviewParameterProvider
import com.stathis.common.util.DimenRes
import com.stathis.model.characters.CharacterStatus

private const val SIXTY_PERCENT = 0.6f

/**
 * Composable used to display the [com.stathis.model.characters.CharacterResponse] status along with the
 * [com.stathis.model.characters.CharacterResponse] displayName
 */

@Composable
internal fun BoxScope.CharacterLabel(
    modifier: Modifier = Modifier,
    characterDisplayName: String,
    characterStatus: CharacterStatus
) {
    Column(
        modifier = modifier
            .wrapContentHeight()
            .padding()
            .clip(shape = RoundedCornerShape(dimensionResource(DimenRes.dimen_8)))
            .background(Color.Black.copy(alpha = SIXTY_PERCENT))
            .align(alignment = Alignment.BottomCenter),
    ) {
        CharacterStatusLabel(characterStatus = characterStatus)
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = dimensionResource(DimenRes.dimen_8)),
            textAlign = TextAlign.Center,
            text = characterDisplayName,
            color = Color.White,
            style = TextStyle(
                fontSize = MaterialTheme.typography.titleLarge.fontSize
            )
        )
    }
}

@Preview
@Composable
private fun CharacterLabelPreview(
    @PreviewParameter(CharacterStatusPreviewParameterProvider::class) status: CharacterStatus
) {
    Box {
        CharacterLabel(
            characterStatus = status,
            characterDisplayName = "Morty"
        )
    }
}
