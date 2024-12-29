package com.stathis.characters.ui.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stathis.designsystem.theme.RickAndMortyAppTheme
import com.stathis.model.characters.CharacterResponse
import com.stathis.testing.CharactersFakes
import com.stathis.util.util.DimenRes

private const val COLUMNS = 2

@Composable
internal fun CharacterList(
    paddingValues: PaddingValues,
    characters: List<CharacterResponse>,
    onCharacterClick: (Int) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(COLUMNS),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(DimenRes.dimen_8)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(DimenRes.dimen_8)),
        modifier = Modifier
            .navigationBarsPadding()
            .padding(top = paddingValues.calculateTopPadding())
            .padding(horizontal = dimensionResource(DimenRes.dimen_8)),
        content = {
            items(items = characters, key = { it.id }) { character ->
                CharacterCard(
                    character = character,
                    onCharacterClick = onCharacterClick
                )
            }
        }
    )
}

@Preview
@Composable
private fun CharacterListPreview() {
    val dummyList = CharactersFakes.provideDummyCharacterList()
    RickAndMortyAppTheme {
        CharacterList(
            paddingValues = PaddingValues(all = 10.dp),
            characters = dummyList,
            onCharacterClick = {}
        )
    }
}
