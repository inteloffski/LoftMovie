plugins {
    id ("com.android.library")
    id ("kotlin-android")
}

android {
    configureAndroidLibrary(namespace = "com.samarbaeffruslan.asset")
}

dependencies {

    implementation(Dependencies.ANDROID_CORE)
    implementation(Dependencies.APP_COMPAT)
    implementation(Dependencies.MATERIAL)

    testImplementation(Dependencies.JUNIT)
    androidTestImplementation(Dependencies.ANDROID_JUNIT)
    androidTestImplementation(Dependencies.ANDROID_ESPRESSO)
}