package com.stathis.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.stathis.designsystem.components.cards.BasicCardWithImageAndText
import com.stathis.model.characters.CharacterResponse
import com.stathis.testing.DUMMY_CHARACTER

@Composable
fun CharacterDisplayCard(
    modifier: Modifier = Modifier,
    character: CharacterResponse,
    onCharacterClick: (Int) -> Unit
) {
    BasicCardWithImageAndText(
        modifier = modifier,
        title = character.name,
        description = character.species,
        imageUrl = character.image,
        contentDescription = character.name,
        onClick = {
            onCharacterClick.invoke(character.id)
        }
    )
}

@Preview
@Composable
private fun CharacterDisplayCardPreview() {
    CharacterDisplayCard(
        modifier = Modifier.fillMaxWidth(),
        character = DUMMY_CHARACTER,
        onCharacterClick = {}
    )
}
