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
import com.stathis.common.util.DimenRes
import com.stathis.common.util.StringRes
import com.stathis.designsystem.components.cards.BasicCardWithText
import com.stathis.model.episodes.Episode

internal fun LazyListScope.displayEpisodeList(
    episodes: List<Episode>,
    onEpisodeClick: (Int) -> Unit
) {
    item {
        Text(
            modifier = Modifier.padding(
                top = dimensionResource(DimenRes.dimen_16),
                start = dimensionResource(DimenRes.dimen_16)
            ),
            text = stringResource(StringRes.episodes),
            style = TextStyle(
                fontSize = MaterialTheme.typography.titleLarge.fontSize
            )
        )
    }
    items(items = episodes) {
        BasicCardWithText(
            modifier = Modifier.padding(
                top = dimensionResource(DimenRes.dimen_8),
                start = dimensionResource(DimenRes.dimen_16),
                end = dimensionResource(DimenRes.dimen_16),
            ),
            title = it.name + " | " + it.episode,
            description = it.airDate,
            onItemClick = { onEpisodeClick(it.id) }
        )
    }
}
