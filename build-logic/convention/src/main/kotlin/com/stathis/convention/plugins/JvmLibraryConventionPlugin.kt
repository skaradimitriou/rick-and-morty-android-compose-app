package com.stathis.convention.plugins

import com.stathis.convention.ext.configureKotlinJvm
import org.gradle.api.Plugin
import org.gradle.api.Project

internal class JvmLibraryConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.jvm")
            }
            configureKotlinJvm()
        }
    }
}