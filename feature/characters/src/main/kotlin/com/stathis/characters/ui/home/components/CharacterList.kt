package com.stathis.characters.ui.home.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stathis.common.util.DimenRes
import com.stathis.designsystem.theme.RickAndMortyAppTheme
import com.stathis.model.characters.CharacterResponse
import com.stathis.testing.CharactersFakes
import com.stathis.ui.CharacterDisplayCard

@Composable
internal fun CharacterList(
    paddingValues: PaddingValues,
    characters: List<CharacterResponse>,
    onCharacterClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .navigationBarsPadding()
            .padding(top = paddingValues.calculateTopPadding())
    ) {
        items(items = characters) { character ->
            CharacterDisplayCard(
                modifier = Modifier.padding(
                    top = dimensionResource(DimenRes.dimen_8),
                    start = dimensionResource(DimenRes.dimen_16),
                    end = dimensionResource(DimenRes.dimen_16),
                ),
                character = character,
                onCharacterClick = onCharacterClick
            )
        }
    }
}

@Preview
@Composable
fun CharacterListPreview() {
    val dummyList = CharactersFakes.provideDummyCharacterList()
    RickAndMortyAppTheme {
        CharacterList(
            paddingValues = PaddingValues(all = 10.dp),
            characters = dummyList,
            onCharacterClick = {}
        )
    }
}
