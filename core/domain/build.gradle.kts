plugins {
    alias(libs.plugins.custom.android.library)
    alias(libs.plugins.custom.android.hilt)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.stathis.domain"
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":core:model"))
}