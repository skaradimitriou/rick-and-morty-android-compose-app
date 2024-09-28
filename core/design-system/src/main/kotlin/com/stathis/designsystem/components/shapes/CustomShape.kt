package com.stathis.designsystem.components.shapes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CustomShape(
    modifier: Modifier = Modifier,
    shape: Shape,
    size: Dp = 32.dp,
    backgroundColor: Color
) {
    Column(
        modifier = modifier
            .wrapContentSize(Alignment.Center)
    ) {
        Box(
            modifier = Modifier
                .size(size)
                .clip(shape)
                .background(backgroundColor)
        )
    }
}

@Preview
@Composable
fun CustomShapeCirclePreview(modifier: Modifier = Modifier) {
    CustomShape(
        shape = CircleShape,
        size = 50.dp,
        backgroundColor = Color.Gray
    )
}

@Preview
@Composable
fun CustomShapeRectPreview(modifier: Modifier = Modifier) {
    CustomShape(
        shape = RectangleShape,
        size = 50.dp,
        backgroundColor = Color.Gray
    )
}