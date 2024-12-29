package com.stathis.locations.ui.details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stathis.designsystem.components.cards.PosterCardWithLabel
import com.stathis.locations.provider.PREVIEW_LOCATION
import com.stathis.model.location.Location
import com.stathis.util.util.DimenRes
import com.stathis.util.util.StringRes
import com.stathis.util.util.toNotNull

internal fun LazyListScope.displayBasicLocationInfo(
    location: Location
) {
    item {
        PosterCardWithLabel(
            modifier = Modifier.height(dimensionResource(DimenRes.dimen_350)),
            label = {
                Column(
                    modifier = Modifier.padding(all = dimensionResource(DimenRes.dimen_8)),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = location.name.toNotNull(),
                        style = TextStyle(
                            color = Color.White,
                            fontSize = MaterialTheme.typography.titleLarge.fontSize
                        )
                    )
                    SpannableText(
                        modifier = Modifier.padding(top = dimensionResource(DimenRes.dimen_10)),
                        textInBold = stringResource(StringRes.type),
                        value = location.type
                    )
                    SpannableText(
                        modifier = Modifier.padding(top = dimensionResource(DimenRes.dimen_4)),
                        textInBold = stringResource(StringRes.dimension),
                        value = location.dimension
                    )
                }
            }
        )
    }
}

@Composable
private fun SpannableText(
    modifier: Modifier = Modifier,
    textInBold: String,
    value: String
) {
    val annotatedString = buildAnnotatedString {
        withStyle(
            SpanStyle(
                color = Color.White,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                fontWeight = FontWeight.W700
            )
        ) {
            append(textInBold)
        }

        append(" ")

        withStyle(
            SpanStyle(
                color = Color.White,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize
            )
        ) {
            append(value)
        }
    }

    Text(
        modifier = modifier,
        text = annotatedString
    )
}

@Preview(showBackground = true)
@Composable
private fun DisplayBasicLocationInfoPreview() {
    LazyColumn {
        displayBasicLocationInfo(PREVIEW_LOCATION)
    }
}

@Preview()
@Composable
private fun SpannableTextPreview() {
    SpannableText(
        modifier = Modifier.padding(8.dp),
        textInBold = stringResource(StringRes.type),
        value = "Normal"
    )
}
