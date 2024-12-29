package com.stathis.designsystem.components.images

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun CoilImage(
    modifier: Modifier = Modifier,
    imageUrlToLoad: String? = null,
    contentDescription: String? = null,
    placeHolder: Int = com.stathis.util.R.drawable.image_placeholder
) {
    AsyncImage(
        modifier = modifier,
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrlToLoad)
            .crossfade(true)
            .placeholder(placeHolder)
            .error(placeHolder)
            .build(),
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop
    )
}

@Preview
@Composable
fun CoilImagePreview() {
    CoilImage(
        imageUrlToLoad = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c1/Google_%22G%22_logo.svg/768px-Google_%22G%22_logo.svg.png"
    )
}
