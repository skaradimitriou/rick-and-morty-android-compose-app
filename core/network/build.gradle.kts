plugins {
    alias(libs.plugins.custom.android.library)
    alias(libs.plugins.custom.android.koin)
    alias(libs.plugins.custom.test.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.stathis.network"

    buildTypes {
        android.buildFeatures.buildConfig = true

        debug {
            buildConfigField("String", "API_URL", "\"https://rickandmortyapi.com/api/\"")
        }
        release {
            buildConfigField("String", "API_URL", "\"https://rickandmortyapi.com/api/\"")
        }
    }
}

dependencies {
    implementation(libs.retrofit.gson)
    implementation(libs.retrofit)
    implementation(libs.retrofit.logger)


    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.content.negotiation)
    implementation(libs.ktor.serialization.gson)
    implementation(libs.ktor.client.logging)
}
