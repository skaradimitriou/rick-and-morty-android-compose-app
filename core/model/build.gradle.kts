plugins {
    alias(libs.plugins.custom.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.stathis.model"
}

dependencies {
    implementation(libs.androidx.core.ktx)
}
