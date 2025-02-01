import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

object AndroidConfig {
    const val compileSdk = 34
    const val minSdk = 21
    const val targetSdk = 34
    const val jvmTarget = "17"


}

fun Project.configureAndroidLibrary(namespace: String) {
    plugins.apply("com.android.library")
    plugins.apply("kotlin-android")
    plugins.apply("kotlin-kapt")

    extensions.configure<LibraryExtension> {
        this.namespace = namespace
        compileSdk = AndroidConfig.compileSdk

        defaultConfig {
            minSdk = AndroidConfig.minSdk
            targetSdk = AndroidConfig.targetSdk
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }
    }
}