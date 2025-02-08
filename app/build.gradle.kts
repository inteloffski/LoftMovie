plugins {
    id ("com.android.application")
    id ("kotlin-android")
    id ("kotlin-kapt")
}

android {
    configureApplication(namespace = "com.example.loftmovie")
}

dependencies {

    implementation(Dependencies.ANDROID_CORE)
    implementation(Dependencies.APP_COMPAT)
    implementation(Dependencies.MATERIAL)

    testImplementation(Dependencies.JUNIT)
    androidTestImplementation(Dependencies.ANDROID_JUNIT)
    androidTestImplementation(Dependencies.ANDROID_ESPRESSO)

    implementation(Dependencies.CONSTRAINT_LAYOUT)

    implementation(project(":core"))
    implementation(project(":main"))
    implementation(project(":asset"))

    implementation(Dependencies.DAGGER)
    kapt(Dependencies.DAGGER_COMPILER)
}