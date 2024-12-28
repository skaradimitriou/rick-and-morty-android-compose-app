package com.stathis.episodes.ui.details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.stathis.common.util.DimenRes
import com.stathis.common.util.StringRes
import com.stathis.designsystem.components.cards.PosterCardWithLabel
import com.stathis.episodes.providers.EPISODE
import com.stathis.model.episodes.Episode

internal fun LazyListScope.displayEpisodeInfo(episode: Episode) {
    item {
        PosterCardWithLabel(
            modifier = Modifier
                .height(dimensionResource(DimenRes.dimen_350)),
            label = {
                EpisodeDetailsLabel(
                    episodeName = episode.name,
                    episodeNo = episode.episode,
                    airDate = episode.airDate
                )
            }
        )
    }
}

@Composable
private fun EpisodeDetailsLabel(
    episodeName: String,
    episodeNo: String,
    airDate: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = dimensionResource(DimenRes.dimen_8)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = episodeName,
            style = TextStyle(
                color = Color.White,
                fontSize = MaterialTheme.typography.titleLarge.fontSize
            )
        )
        Spacer(modifier = Modifier.height(dimensionResource(DimenRes.dimen_8)))
        Text(
            text = episodeNo.plus(stringResource(StringRes.divider)).plus(airDate),
            style = TextStyle(
                color = Color.White,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize
            )
        )
    }
}

@Preview
@Composable
private fun EpisodeDetailsLabelPreview() {
    EpisodeDetailsLabel(
        episodeName = EPISODE.name,
        episodeNo = EPISODE.episode,
        airDate = EPISODE.airDate,
    )
}

@Preview
@Composable
private fun DisplayEpisodeInfoPreview() {
    LazyColumn {
        displayEpisodeInfo(EPISODE)
    }
}
