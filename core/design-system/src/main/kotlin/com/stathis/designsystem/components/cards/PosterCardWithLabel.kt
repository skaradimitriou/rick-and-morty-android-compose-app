package com.stathis.designsystem.components.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.stathis.common.util.DimenRes
import com.stathis.designsystem.components.images.CoilImage
import com.stathis.designsystem.consts.IMAGE_URL
import com.stathis.designsystem.consts.TITLE

private const val SIXTY_PERCENT = 0.6f

@Composable
fun PosterCardWithLabel(
    modifier: Modifier = Modifier,
    imageToLoad: String? = null,
    label: @Composable () -> Unit
) {
    Card {
        Box(modifier = modifier.fillMaxSize()) {
            CoilImage(
                modifier = Modifier.fillMaxSize(),
                imageUrlToLoad = imageToLoad
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = dimensionResource(DimenRes.dimen_10))
                    .clip(shape = RoundedCornerShape(dimensionResource(DimenRes.dimen_8)))
                    .background(Color.Black.copy(alpha = SIXTY_PERCENT))
                    .align(alignment = Alignment.BottomCenter),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                label()
            }
        }
    }
}

@Preview
@Composable
private fun PosterWithInformationPreview() {
    PosterCardWithLabel(
        imageToLoad = IMAGE_URL,
        label = {
            Text(
                modifier = Modifier.padding(all = dimensionResource(DimenRes.dimen_10)),
                text = TITLE,
                style = TextStyle(
                    color = Color.White,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize
                )
            )
        }
    )
}
