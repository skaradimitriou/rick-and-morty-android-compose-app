package com.stathis.ui.topbars

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.stathis.designsystem.components.topbar.CustomTopAppBar
import com.stathis.util.util.Callback

@Composable
fun TopBarWithBackNavIcon(title: String, onBackNavigationIconClick: Callback) {
    CustomTopAppBar(
        startIcon = Icons.AutoMirrored.Default.ArrowBack,
        startIconContentDesc = "Back Navigation Arrow",
        startIconCallback = onBackNavigationIconClick,
        title = title
    )
}

@Preview(showBackground = true)
@Composable
private fun TopBarWithBackNavIconPreview() {
    TopBarWithBackNavIcon(
        title = "My screen title",
        onBackNavigationIconClick = {}
    )
}
