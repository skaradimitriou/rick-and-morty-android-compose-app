package com.stathis.characters.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import com.stathis.designsystem.components.cards.BasicCardWithText
import com.stathis.model.episodes.Episode
import com.stathis.util.util.DimenRes
import com.stathis.util.util.StringRes

internal fun LazyListScope.displayEpisodeList(
    episodes: List<Episode>,
    onEpisodeClick: (Int) -> Unit
) {
    item {
        Text(
            modifier = Modifier.padding(top = dimensionResource(DimenRes.dimen_16)),
            text = stringResource(StringRes.episodes),
            style = TextStyle(
                fontSize = MaterialTheme.typography.titleLarge.fontSize
            )
        )
    }
    if (episodes.isEmpty()) {
        item {
            Text(
                modifier = Modifier.padding(
                    top = dimensionResource(DimenRes.dimen_8),
                    bottom = dimensionResource(DimenRes.dimen_16)
                ),
                text = stringResource(StringRes.empty_results),
                style = TextStyle(fontSize = MaterialTheme.typography.bodyLarge.fontSize)
            )
        }
    } else {
        items(items = episodes) {
            BasicCardWithText(
                modifier = Modifier.padding(top = dimensionResource(DimenRes.dimen_8)),
                title = it.name + " | " + it.episode,
                description = it.airDate,
                onItemClick = { onEpisodeClick(it.id) }
            )
        }
    }
}
