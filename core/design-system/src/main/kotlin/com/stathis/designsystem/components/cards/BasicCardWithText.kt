package com.stathis.designsystem.components.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.stathis.common.util.Callback
import com.stathis.common.util.DimenRes

@Composable
fun BasicCardWithText(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    onItemClick: Callback? = null,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onItemClick?.invoke() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(DimenRes.dimen_16))
        ) {
            Text(
                text = title,
                style = TextStyle(
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                )
            )
            Text(
                modifier = Modifier.padding(top = dimensionResource(DimenRes.dimen_8)),
                text = description,
                style = TextStyle(
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize
                )
            )
        }
    }
}

@Preview
@Composable
fun BasicCardWithTextPreview(modifier: Modifier = Modifier) {
    BasicCardWithText(
        title = "This is a title",
        description = "This is a description"
    )
}
