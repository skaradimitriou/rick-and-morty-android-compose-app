import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.stathis.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.room.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidLibrary") {
            id = "custom.android.library"
            implementationClass = "com.stathis.convention.plugins.AndroidLibraryConventionPlugin"
        }
        register("androidHilt") {
            id = "custom.android.hilt"
            implementationClass = "com.stathis.convention.plugins.AndroidHiltConventionPlugin"
        }
        register("androidCompose") {
            id = "custom.android.compose"
            implementationClass = "com.stathis.convention.plugins.AndroidLibraryComposeConventionPlugin"
        }
        register("androidFeature") {
            id = "custom.android.feature"
            implementationClass = "com.stathis.convention.plugins.AndroidFeatureConventionPlugin"
        }
        register("jvmLibrary") {
            id = "custom.android.jvm"
            implementationClass = "com.stathis.convention.plugins.JvmLibraryConventionPlugin"
        }
        register("androidRoom") {
            id = "custom.android.room"
            implementationClass = "com.stathis.convention.plugins.AndroidRoomConventionPlugin"
        }
        register("androidTest") {
            id = "custom.android.test"
            implementationClass = "com.stathis.convention.plugins.AndroidTestConventionPlugin"
        }
    }
}
