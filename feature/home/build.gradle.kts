plugins {
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.custom.android.feature.library)
    alias(libs.plugins.custom.android.compose)
}

android {
    namespace = "com.stathis.home"
}

dependencies {
    implementation(projects.core.designSystem)
    implementation(projects.core.common)
    implementation(projects.core.domain)
    implementation(projects.core.model)

    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.lifecycle.runtime.ktx)
}