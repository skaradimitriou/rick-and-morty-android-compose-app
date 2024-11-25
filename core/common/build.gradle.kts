plugins {
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.custom.android.library)
    alias(libs.plugins.custom.android.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.stathis.common"
}

dependencies {
    implementation(libs.kotlin.serialization.json)
}
