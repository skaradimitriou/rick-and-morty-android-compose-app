plugins {
    alias(libs.plugins.custom.android.library)
    alias(libs.plugins.custom.test.library)
    alias(libs.plugins.custom.android.koin)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.stathis.domain"
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.model)

    implementation(libs.kotlinx.coroutines)
}
