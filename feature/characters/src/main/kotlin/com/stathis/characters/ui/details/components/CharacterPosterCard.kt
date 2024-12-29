package com.stathis.characters.ui.details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.stathis.characters.ui.details.providers.CharacterStatusPreviewParameterProvider
import com.stathis.model.characters.CharacterStatus
import com.stathis.util.util.DimenRes

@Composable
internal fun CharacterLabel(
    characterDisplayName: String,
    characterStatus: CharacterStatus
) {
    Column(
        modifier = Modifier.wrapContentHeight(),
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
    CharacterLabel(
        characterStatus = status,
        characterDisplayName = "Morty"
    )
}
