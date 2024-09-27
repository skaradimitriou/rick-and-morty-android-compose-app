package com.stathis.designsystem.components.topbar

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.stathis.common.util.Callback

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    startIcon: ImageVector? = null,
    startIconContentDesc: String? = null,
    startIconCallback: Callback? = null,
    title: String
) {
    TopAppBar(
        navigationIcon = {
            startIcon?.let {
                IconButton(onClick = { startIconCallback?.invoke() }) {
                    Icon(
                        imageVector = startIcon,
                        contentDescription = startIconContentDesc
                    )
                }
            }
        },
        title = {
            Text(text = title)
        }
    )
}