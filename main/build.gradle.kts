plugins {
    id ("com.android.library")
    id ("kotlin-android")
    id ("kotlin-kapt")
}

android {
    configureAndroidLibrary(namespace = "com.samarbaeffruslan.main")
}

dependencies {
    implementation(project(":core"))
    implementation(project(":search"))
    implementation(project(":popular"))
    implementation(project(":favorite"))
    implementation(project(":splash"))
    implementation(project(":asset"))
    implementation(project(":detail"))

    implementation(Dependencies.MATERIAL)
    implementation(Dependencies.DAGGER)
    kapt(Dependencies.DAGGER_COMPILER)
    implementation(Dependencies.VIEW_BINDING_DELEGATE)
    implementation(Dependencies.RETROFIT_CONVERTER_GSON)
    implementation(Dependencies.ANDROID_CORE)
    implementation(Dependencies.APP_COMPAT)
    implementation(Dependencies.CONSTRAINT_LAYOUT)
    Dependencies.navigations.forEach(::implementation)

    testImplementation(Dependencies.JUNIT)
    androidTestImplementation(Dependencies.ANDROID_JUNIT)
    androidTestImplementation(Dependencies.ANDROID_ESPRESSO)

}