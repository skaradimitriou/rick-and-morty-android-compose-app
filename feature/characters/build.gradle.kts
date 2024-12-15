plugins {
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.custom.android.feature.library)
    alias(libs.plugins.custom.android.koin)
    alias(libs.plugins.custom.android.compose)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.stathis.characters"
}

dependencies {
    implementation(projects.core.designSystem)
    implementation(projects.core.common)
    implementation(projects.core.domain)
    implementation(projects.core.model)
    implementation(projects.core.ui)
    implementation(projects.core.navigation)
    implementation(projects.core.testing)

    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlin.serialization.json)

    implementation(libs.androidx.lifecycle.runtime.ktx)
}
