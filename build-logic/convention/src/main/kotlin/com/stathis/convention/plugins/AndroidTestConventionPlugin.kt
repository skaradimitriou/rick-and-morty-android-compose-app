package com.stathis.convention.plugins

import com.stathis.convention.ext.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidTestConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            dependencies {
                add("implementation", project(":core:testing"))
                add("testImplementation", libs.findLibrary("mockk").get())
                add("testImplementation", libs.findLibrary("kotlinx.coroutines.test").get())
                add("testImplementation", libs.findLibrary("junit.jupiter.api").get())
                add("testImplementation", libs.findLibrary("junit.jupiter.engine").get())
                add("androidTestImplementation", libs.findLibrary("mockk").get())
            }
        }
    }
}
