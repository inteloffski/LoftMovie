plugins {
    id ("com.android.library")
    id ("kotlin-android")
    id ("kotlin-kapt")
}

android {
    configureAndroidLibrary(namespace = "com.example.popular")
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

    implementation(Dependencies.CARD_VIEW)
    implementation(Dependencies.CONSTRAINT_LAYOUT)
    implementation(Dependencies.RECYCLER_VIEW)

    implementation(Dependencies.DAGGER)
    kapt(Dependencies.DAGGER_COMPILER)

    Dependencies.navigations.forEach(::implementation)

    implementation(Dependencies.RETROFIT)
    
    // Glide
    implementation(Dependencies.GLIDE)
    kapt(Dependencies.GLIDE_COMPILER)

    //pagination library
    implementation(Dependencies.PAGING)
    implementation(Dependencies.PAGING_RX)
    implementation(Dependencies.RX_ANDROID)

    //viewdelegate
    implementation(Dependencies.VIEW_BINDING_DELEGATE)

    //swipe to refresh
    implementation(Dependencies.SWIPE_REFRESH_LAYOUT)



}