plugins {
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.custom.android.library)
    alias(libs.plugins.custom.android.hilt)
}

android {
    namespace = "com.stathis.data"

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.network)
    implementation(projects.core.model)

    implementation(libs.retrofit)

    testImplementation(libs.kotlinx.coroutines.test)

}