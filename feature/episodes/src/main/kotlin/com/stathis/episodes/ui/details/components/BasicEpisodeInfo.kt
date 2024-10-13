package com.stathis.episodes.ui.details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import com.stathis.common.util.DimenRes
import com.stathis.common.util.StringRes
import com.stathis.common.util.toNotNull
import com.stathis.model.episodes.Episode

fun LazyListScope.displayEpisodeInfo(episode: Episode) {
    item {
        Column(
            modifier = Modifier.padding(
                horizontal = dimensionResource(DimenRes.dimen_16),
                vertical = dimensionResource(DimenRes.dimen_16)
            )
        ) {
            Text(
                text = episode.name.toNotNull(),
                style = TextStyle(
                    fontSize = MaterialTheme.typography.titleLarge.fontSize
                )
            )
            Spacer(modifier = Modifier.height(dimensionResource(DimenRes.dimen_8)))
            Text(
                text = stringResource(StringRes.episode_name, episode.episode),
                style = TextStyle(
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize
                )
            )
            Spacer(modifier = Modifier.height(dimensionResource(DimenRes.dimen_8)))
            Text(
                text = stringResource(StringRes.episode_name, episode.airDate),
                style = TextStyle(
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize
                )
            )
        }
    }
}
