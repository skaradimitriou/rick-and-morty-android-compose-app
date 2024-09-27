plugins {
    alias(libs.plugins.custom.android.library)
    alias(libs.plugins.custom.android.hilt)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.stathis.domain"
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.model)
}