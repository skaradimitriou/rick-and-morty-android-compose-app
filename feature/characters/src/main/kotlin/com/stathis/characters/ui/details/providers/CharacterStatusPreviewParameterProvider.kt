package com.stathis.characters.ui.details.providers

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.stathis.model.characters.CharacterStatus

/**
 * PreviewParameterProvider that returns a sequence of [CharacterStatus] entries for preview purposes
 */

internal class CharacterStatusPreviewParameterProvider : PreviewParameterProvider<CharacterStatus> {

    override val values = CharacterStatus.entries.asSequence()
}
