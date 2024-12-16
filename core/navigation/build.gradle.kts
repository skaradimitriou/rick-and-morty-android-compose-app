plugins {
    alias(libs.plugins.custom.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.custom.android.compose)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.stathis.navigation"
}

dependencies {
    implementation(projects.core.common)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.navigation.compose)

    implementation(libs.kotlin.serialization.json)
}
