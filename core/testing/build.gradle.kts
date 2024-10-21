plugins {
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.custom.android.library)
}

android {
    namespace = "com.stathis.testing"
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.domain)
    implementation(libs.kotlinx.coroutines)

    testImplementation(libs.androidx.core.testing)
}
