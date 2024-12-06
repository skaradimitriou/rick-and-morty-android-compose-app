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
}
