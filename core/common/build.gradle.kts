plugins {
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.custom.android.library)
    alias(libs.plugins.custom.android.koin)
}

android {
    namespace = "com.stathis.common"
}
