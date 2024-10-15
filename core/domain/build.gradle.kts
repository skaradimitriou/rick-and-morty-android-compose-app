plugins {
    alias(libs.plugins.custom.android.library)
    alias(libs.plugins.custom.android.hilt)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.stathis.domain"
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.model)
    implementation(projects.core.testing)

    implementation(libs.kotlinx.coroutines)
}
