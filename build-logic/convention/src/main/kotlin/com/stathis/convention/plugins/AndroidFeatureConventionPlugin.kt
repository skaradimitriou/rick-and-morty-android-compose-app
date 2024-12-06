package com.stathis.convention.plugins

import com.stathis.convention.ext.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("custom.android.library")
            }

            dependencies {
                add("implementation", project(":core:design-system"))
                add("implementation", libs.findLibrary("koin.compose.viewmodel.navigation").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.runtimeCompose").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.viewModelCompose").get())
            }
        }
    }
}
