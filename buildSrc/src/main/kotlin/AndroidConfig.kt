import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

object AndroidConfig {
    const val COMPILE_SDK = 34
    const val MIN_SDK = 21
    const val TARGET_SDK = 34
}

fun Project.configureAndroidLibrary(namespace: String) {
    plugins.apply("com.android.library")
    plugins.apply("kotlin-android")
    plugins.apply("kotlin-kapt")

    extensions.configure<LibraryExtension> {
        this.namespace = namespace
        compileSdk = AndroidConfig.COMPILE_SDK

        buildFeatures {
            viewBinding = true
        }

        defaultConfig {
            minSdk = AndroidConfig.MIN_SDK
            targetSdk = AndroidConfig.TARGET_SDK
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }
    }
}