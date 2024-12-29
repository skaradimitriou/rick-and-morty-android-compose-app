package com.stathis.episodes.ui.details.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import com.stathis.model.characters.CharacterResponse
import com.stathis.ui.CharacterDisplayCard
import com.stathis.util.util.DimenRes
import com.stathis.util.util.StringRes

internal fun LazyListScope.displayCharacters(
    characters: List<CharacterResponse>,
    onCharacterClick: (Int) -> Unit
) {
    item {
        Text(
            modifier = Modifier.padding(vertical = dimensionResource(DimenRes.dimen_16)),
            text = stringResource(StringRes.characters),
            style = TextStyle(
                fontSize = MaterialTheme.typography.titleLarge.fontSize
            )
        )
    }
    items(characters) { character ->
        CharacterDisplayCard(
            modifier = Modifier.padding(top = dimensionResource(DimenRes.dimen_8)),
            character = character,
            onCharacterClick = onCharacterClick
        )
    }
}
