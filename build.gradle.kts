// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    extra["kotlin_version"] = "1.9.0"

    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.3.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.0")
    }

}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}