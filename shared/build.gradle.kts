import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinx.serialization)
}

kotlin {
    
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    js(IR) {
        moduleName = "composeApp"
        browser {
            val rootDirPath = project.rootDir.path
            val projectDirPath = project.projectDir.path
            commonWebpackConfig {
                outputFileName = "composeApp.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        add(rootDirPath)
                        add(projectDirPath)
                    }
                }
            }
        }
        binaries.executable()
        useEsModules()
    }

    sourceSets {

        jsMain.dependencies {
            implementation(libs.kstore.storage)
            implementation(libs.ktor.client.js)
            implementation(libs.jackson.module.kotlin)

            implementation("io.ktor:ktor-client-core:2.3.0")
            implementation("io.ktor:ktor-client-js:2.3.0") // Ensure JS support if targeting Kotlin/JS

        }

        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.kstore.file)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.koin.android)
            implementation(libs.koin.android.compose)
            implementation(libs.ktor.client.android)
            implementation(libs.androidx.media3.exoplayer)
            implementation(libs.firebase.messaging.ktx)
        }

        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.materialIconsExtended)
            implementation(compose.ui)
            implementation(compose.material3AdaptiveNavigationSuite)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)
            implementation(libs.navigation.compose)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.kstore)
            implementation(libs.multiplatform.settings)
            implementation(libs.multiplatform.settings.no.arg)
            implementation(libs.material3.window.size.class1)
            implementation(libs.kotlinx.collections.immutable)
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.vm)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.client.encoding)
            implementation(libs.ktor.client.serialization)
            implementation(libs.ktor.client.serialization.json)

            implementation(libs.ktor.client.logging)
            implementation(libs.ktor.client.auth)
            implementation(libs.ktor.client.auth)
            implementation(libs.compose.shimmer)

            implementation(libs.coil.compose.core)
            implementation(libs.coil.compose)
            implementation(libs.coil.mp)
            implementation(libs.coil.network.ktor)

            implementation("media.kamel:kamel-image:1.0.3")
            implementation("media.kamel:kamel-decoder-image-bitmap:1.0.3")
            implementation("media.kamel:kamel-decoder-image-vector:1.0.3")
            implementation("media.kamel:kamel-decoder-animated-image:1.0.3")

        }

    }
}


android {
    namespace = "m.a.nobahar"
    compileSdk = 35
    defaultConfig {
        minSdk = 23
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        buildConfig = true
    }
}