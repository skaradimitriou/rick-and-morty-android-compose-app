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
import com.stathis.designsystem.components.cards.BasicCardWithImageAndText
import com.stathis.designsystem.theme.RickAndMortyAppTheme
import com.stathis.model.characters.CharacterResponse
import com.stathis.model.characters.CharacterStatus

@Composable
internal fun CharacterList(
    paddingValues: PaddingValues,
    characters: List<CharacterResponse>,
    onCharacterClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .navigationBarsPadding()
            .padding(horizontal = dimensionResource(DimenRes.dimen_8))
            .padding(top = paddingValues.calculateTopPadding())
    ) {
        items(items = characters) { character ->
            BasicCardWithImageAndText(
                title = character.name,
                description = character.species,
                imageUrl = character.image,
                contentDescription = character.name,
                onClick = {
                    onCharacterClick(character.id)
                }
            )
        }
    }
}

@Preview
@Composable
fun CharacterListPreview() {
    val dummyList = listOf(
        CharacterResponse(
            123, "Character Name", CharacterStatus.ALIVE, "Human", "Type",
            "Male", "", "Earth", "", listOf("8"), "", ""
        ),
        CharacterResponse(
            123, "Character Name", CharacterStatus.ALIVE, "Human", "Type",
            "Male", "", "Earth", "", listOf("8"), "", ""
        ),
        CharacterResponse(
            123, "Character Name", CharacterStatus.ALIVE, "Human", "Type",
            "Male", "", "Earth", "", listOf("8"), "", ""
        ),
        CharacterResponse(
            123, "Character Name", CharacterStatus.ALIVE, "Human", "Type",
            "Male", "", "Earth", "", listOf("8"), "", ""
        ),
        CharacterResponse(
            123, "Character Name", CharacterStatus.ALIVE, "Human", "Type",
            "Male", "", "Earth", "", listOf("8"), "", ""
        )
    )

    RickAndMortyAppTheme {
        CharacterList(
            paddingValues = PaddingValues(all = 10.dp),
            characters = dummyList,
            onCharacterClick = {}
        )
    }
}
