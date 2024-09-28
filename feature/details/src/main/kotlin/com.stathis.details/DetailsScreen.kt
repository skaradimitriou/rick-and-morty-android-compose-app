package com.stathis.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.stathis.common.util.Callback
import com.stathis.common.util.StringRes
import com.stathis.designsystem.components.images.CoilImage
import com.stathis.designsystem.components.shapes.CustomShape
import com.stathis.designsystem.components.topbar.CustomTopAppBar

@Composable
internal fun DetailsScreen(
    viewModel: DetailsScreenViewModel = hiltViewModel(),
    onBackNavIconClick: Callback
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = true) {
        viewModel.fetchCharacterInformation()
    }

    DetailsContent(
        uiState = uiState.data,
        onBackNavIconClick = onBackNavIconClick
    )
}

@Composable
internal fun DetailsContent(
    uiState: String,
    onBackNavIconClick: Callback
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CustomTopAppBar(
                startIcon = Icons.Default.ArrowBack,
                startIconContentDesc = "Back Navigation Arrow",
                startIconCallback = onBackNavIconClick,
                title = stringResource(StringRes.details)
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                CoilImage(
                    modifier = Modifier.height(300.dp),
                    imageUrlToLoad = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c1/Google_%22G%22_logo.svg/768px-Google_%22G%22_logo.svg.png"
                )
                Spacer(modifier = Modifier.height(30.dp))

                BasicCharacterInfo(
                    characterName = "Morty Smith",
                    isAlive = true,
                    status = "Alive"
                )

                CharacterDetails(
                    species = "Human",
                    gender = "Male",
                    origin = "Earth"
                )
            }
        }
    )
}

@Composable
fun BasicCharacterInfo(
    characterName: String,
    isAlive: Boolean,
    status: String
) {
    val circleIconColor = if (isAlive) Color.Green else Color.Red
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = characterName,
            style = TextStyle(
                fontSize = MaterialTheme.typography.titleLarge.fontSize
            )
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomShape(
                shape = CircleShape,
                size = 16.dp,
                backgroundColor = circleIconColor
            )

            Text(
                modifier = Modifier.padding(start = 10.dp),
                text = status
            )
        }
    }
}

@Preview
@Composable
fun BasicCharacterInfoPreview(modifier: Modifier = Modifier) {
    BasicCharacterInfo(
        characterName = "Morty Smith",
        isAlive = true,
        status = "Alive"
    )
}

@Composable
fun CharacterDetails(
    modifier: Modifier = Modifier,
    species: String,
    gender: String,
    origin: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BasicDetail(
            title = "Species",
            description = species
        )

        VerticalDivider(
            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
        )

        BasicDetail(
            title = "Gender",
            description = gender
        )

        VerticalDivider(
            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
        )

        BasicDetail(
            title = "Origin",
            description = origin
        )
    }
}

@Preview
@Composable
fun CharacterDetailsPreview() {
    CharacterDetails(
        modifier = Modifier.background(Color.White),
        species = "Human",
        gender = "Male",
        origin = "Earth"
    )
}

@Composable
fun BasicDetail(
    modifier: Modifier = Modifier,
    title: String,
    description: String
) {
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = title,
            style = TextStyle(
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = description,
            style = TextStyle(
                fontSize = MaterialTheme.typography.bodyLarge.fontSize
            )
        )
    }
}

@Preview
@Composable
fun BasicDetailPreview() {
    BasicDetail(
        modifier = Modifier.background(Color.White),
        title = "Title",
        description = "Description"
    )
}