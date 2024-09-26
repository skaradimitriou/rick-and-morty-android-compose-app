plugins {
    alias(libs.plugins.custom.android.library)
    alias(libs.plugins.custom.android.hilt)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.stathis.network"
}

dependencies {
    implementation(libs.retrofit.gson)
    implementation(libs.retrofit)
    implementation(libs.retrofit.logger)
}