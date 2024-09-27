package com.stathis.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.stathis.common.util.Callback
import com.stathis.common.util.StringRes
import com.stathis.designsystem.components.topbar.CustomTopAppBar

@Composable
fun DetailsScreen(
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
                //content
            }
        }
    )
}