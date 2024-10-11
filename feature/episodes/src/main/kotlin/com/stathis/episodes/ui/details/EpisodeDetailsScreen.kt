package com.stathis.episodes.ui.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.stathis.common.util.Callback
import com.stathis.common.util.StringRes
import com.stathis.designsystem.components.topbar.CustomTopAppBar
import kotlin.random.Random

@Composable
internal fun EpisodeDetailsScreen(
    episodeId: Int,
    viewModel: EpisodeDetailsViewModel = hiltViewModel(),
    onBackNavIconClick: Callback,
    onCharacterClick: (Int) -> Unit
) {

    EpisodeDetailsContent(
        episodeId = episodeId,
        onBackNavIconClick = onBackNavIconClick,
        onCharacterClick = onCharacterClick
    )
}

@Composable
internal fun EpisodeDetailsContent(
    episodeId: Int,
    onBackNavIconClick: Callback,
    onCharacterClick: (Int) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CustomTopAppBar(
                startIcon = Icons.Default.ArrowBack,
                startIconContentDesc = "Back Navigation Arrow",
                startIconCallback = onBackNavIconClick,
                title = stringResource(StringRes.episode_details)
            )
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                item {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(all = 16.dp)
                            .clickable {
                                val random = Random.nextInt(5) + 1
                                onCharacterClick(random)
                            },
                        text = "You're looking for $episodeId"
                    )
                }
            }
        }
    )
}
