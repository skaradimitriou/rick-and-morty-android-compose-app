package com.stathis.ui.error

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.stathis.common.util.DimenRes
import com.stathis.designsystem.theme.RickAndMortyAppTheme

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    title: String,
    description: String
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            style = TextStyle(
                fontSize = MaterialTheme.typography.titleLarge.fontSize
            )
        )
        Spacer(modifier = Modifier.height(dimensionResource(DimenRes.dimen_10)))
        Text(
            text = description,
            style = TextStyle(
                fontSize = MaterialTheme.typography.bodyLarge.fontSize
            )
        )
    }
}

@Preview
@Composable
fun ErrorScreenPreview() {
    RickAndMortyAppTheme {
        ErrorScreen(
            modifier = Modifier.background(Color.White),
            paddingValues = PaddingValues(all = dimensionResource(DimenRes.dimen_8)),
            title = "Something went wrong",
            description = "My awesome description"
        )
    }
}
