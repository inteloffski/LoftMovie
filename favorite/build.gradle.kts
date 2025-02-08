plugins {
    id ("com.android.library")
    id ("kotlin-android")
    id ("kotlin-kapt")
}

android {
    configureAndroidLibrary(namespace = "com.example.favorite")
}

dependencies {

    implementation(project(":core"))
    implementation(project(":detail"))
    implementation(project(":asset"))

    implementation(Dependencies.ANDROID_CORE)
    implementation(Dependencies.APP_COMPAT)
    implementation(Dependencies.MATERIAL)
    testImplementation(Dependencies.JUNIT)
    androidTestImplementation(Dependencies.ANDROID_JUNIT)
    androidTestImplementation(Dependencies.ANDROID_ESPRESSO)

    implementation(Dependencies.CONSTRAINT_LAYOUT)
    implementation(Dependencies.RECYCLER_VIEW)

    implementation(Dependencies.DAGGER)
    kapt(Dependencies.DAGGER_COMPILER)

    Dependencies.navigations.forEach(::implementation)

    implementation(Dependencies.LIFECYCLE_EXTENSIONS)
    implementation(Dependencies.LIFECYCLE_VIEWMODEL)
    implementation(Dependencies.LIFECYCLE_LIVEDATA)

    implementation(Dependencies.GLIDE)
    kapt(Dependencies.GLIDE_COMPILER)

    implementation(Dependencies.VIEW_BINDING_DELEGATE)

}