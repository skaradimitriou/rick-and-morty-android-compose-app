package com.stathis.characters.ui.details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stathis.common.util.DimenRes
import com.stathis.common.util.toNotNull
import com.stathis.designsystem.components.images.CoilImage
import com.stathis.designsystem.components.shapes.CustomShape
import com.stathis.model.characters.CharacterResponse
import com.stathis.model.characters.CharacterStatus
import com.stathis.testing.CharactersFakes

@Composable
internal fun BasicCharacterInfo(character: CharacterResponse?) {
    val circleIconColor = if (character?.status == CharacterStatus.ALIVE) Color.Green else Color.Red

    CoilImage(
        modifier = Modifier.height(300.dp),
        imageUrlToLoad = character?.image.toNotNull()
    )

    Column(modifier = Modifier.padding(dimensionResource(DimenRes.dimen_16))) {
        Text(
            text = character?.name.toNotNull(),
            style = TextStyle(
                fontSize = MaterialTheme.typography.titleLarge.fontSize
            )
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(DimenRes.dimen_8)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomShape(
                shape = CircleShape,
                size = dimensionResource(DimenRes.dimen_16),
                backgroundColor = circleIconColor
            )

            Text(
                modifier = Modifier.padding(start = dimensionResource(DimenRes.dimen_8)),
                text = character?.status?.name.toNotNull().lowercase().replaceFirstChar { it.uppercase() }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BasicCharacterInfoPreview() {
    val model = CharactersFakes.provideDummyCharacter()
    BasicCharacterInfo(character = model)
}
