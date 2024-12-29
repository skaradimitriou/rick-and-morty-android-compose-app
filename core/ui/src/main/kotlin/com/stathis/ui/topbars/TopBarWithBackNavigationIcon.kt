package com.stathis.ui.topbars

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import com.stathis.designsystem.components.topbar.CustomTopAppBar
import com.stathis.util.util.Callback

@Composable
fun TopBarWithBackNavIcon(title: String, onBackNavigationIconClick: Callback) {
    CustomTopAppBar(
        startIcon = Icons.Default.ArrowBack,
        startIconContentDesc = "Back Navigation Arrow",
        startIconCallback = onBackNavigationIconClick,
        title = title
    )
}
