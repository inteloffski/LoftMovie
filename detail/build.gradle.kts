plugins {
    id ("com.android.library")
    id ("kotlin-android")
    id ("kotlin-kapt")
}

android {
    configureAndroidLibrary(namespace = "com.samarbaeffruslan.detail")
}

dependencies {

    implementation(project(":core"))
    implementation(project(":asset"))

    implementation(Dependencies.ANDROID_CORE)
    implementation(Dependencies.APP_COMPAT)
    implementation(Dependencies.MATERIAL)
    testImplementation(Dependencies.JUNIT)
    androidTestImplementation(Dependencies.ANDROID_JUNIT)
    androidTestImplementation(Dependencies.ANDROID_ESPRESSO)

    implementation(Dependencies.DAGGER)
    kapt(Dependencies.DAGGER_COMPILER)

    Dependencies.navigations.forEach(::implementation)

    implementation(Dependencies.CONSTRAINT_LAYOUT)

    // Glide
    implementation(Dependencies.GLIDE)
    kapt(Dependencies.GLIDE_COMPILER)

    implementation(Dependencies.VIEW_BINDING_DELEGATE)

    //retrofit
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.CARD_VIEW)
}