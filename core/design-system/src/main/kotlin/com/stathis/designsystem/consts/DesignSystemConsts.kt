package com.stathis.designsystem.consts

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum

internal const val IMAGE_URL: String =
    "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c1/Google_%22G%22_logo.svg/768px-Google_%22G%22_logo.svg.png"

internal val TITLE: String = LoremIpsum(words = 5).values.joinToString()

internal val DESCRIPTION: String = LoremIpsum(words = 11).values.joinToString()
