plugins {
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.custom.android.library)
    alias(libs.plugins.custom.android.hilt)
}

android {
    namespace = "com.stathis.common"
}
