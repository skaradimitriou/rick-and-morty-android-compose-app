package com.stathis.characters.ui.details.components

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.stathis.util.util.DimenRes

@Composable
internal fun Detail(
    modifier: Modifier = Modifier,
    title: String,
    description: String
) {
    Card(modifier = modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(dimensionResource(DimenRes.dimen_16))
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
                text = title,
                style = TextStyle(
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(DimenRes.dimen_8)),
                textAlign = TextAlign.Start,
                text = description,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BasicDetailPreview() {
    Detail(
        title = "Title",
        description = "Description"
    )
}
