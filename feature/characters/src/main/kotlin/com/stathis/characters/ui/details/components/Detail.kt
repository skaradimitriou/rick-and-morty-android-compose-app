package com.stathis.characters.ui.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.stathis.common.util.DimenRes

@Composable
internal fun Detail(
    modifier: Modifier = Modifier,
    title: String,
    description: String
) {
    Column(
        modifier = modifier.padding(dimensionResource(DimenRes.dimen_16)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            style = TextStyle(
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            modifier = Modifier.padding(top = dimensionResource(DimenRes.dimen_8)),
            text = description,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(
                fontSize = MaterialTheme.typography.bodyLarge.fontSize
            )
        )
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
