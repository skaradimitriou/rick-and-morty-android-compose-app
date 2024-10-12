package com.stathis.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.stathis.designsystem.components.cards.BasicCardWithImageAndText
import com.stathis.model.characters.CharacterResponse

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
