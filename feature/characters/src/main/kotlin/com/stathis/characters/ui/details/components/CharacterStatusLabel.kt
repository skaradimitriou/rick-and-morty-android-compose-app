package com.stathis.characters.ui.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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

/**
 * Composable used to display a character status label
 * based on the character's [CharacterStatus].
 */

@Composable
internal fun CharacterStatusLabel(
    modifier: Modifier = Modifier,
    characterStatus: CharacterStatus
) {
    val iconColor = remember {
        when (characterStatus) {
            CharacterStatus.ALIVE -> Color.Green
            CharacterStatus.DEAD -> Color.Red
            CharacterStatus.UNKNOWN -> Color.Gray
        }
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(all = dimensionResource(DimenRes.dimen_8)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Absolute.Center
    ) {
        Spacer(
            Modifier
                .size(dimensionResource(DimenRes.dimen_16))
                .clip(CircleShape)
                .background(iconColor)
        )
        Spacer(modifier = Modifier.width(dimensionResource(DimenRes.dimen_8)))
        Text(
            textAlign = TextAlign.Start,
            text = characterStatus.name.lowercase().replaceFirstChar { it.titlecase() },
            color = Color.White,
            style = TextStyle(
                fontSize = MaterialTheme.typography.bodyLarge.fontSize
            )
        )
    }
}

@Preview
@Composable
private fun CharacterStatusLabelPreview(
    @PreviewParameter(CharacterStatusPreviewParameterProvider::class) status: CharacterStatus
) {
    CharacterStatusLabel(characterStatus = status)
}
