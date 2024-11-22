plugins {
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.custom.android.library)
    alias(libs.plugins.custom.android.hilt)
    alias(libs.plugins.custom.test.library)
    alias(libs.plugins.custom.android.room)
}

android {
    namespace = "com.stathis.database"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(projects.core.model)
    implementation(libs.gson)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
