plugins {
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.custom.android.library)
    alias(libs.plugins.custom.android.compose)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.stathis.common"
}
