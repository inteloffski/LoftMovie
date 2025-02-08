plugins {
    id ("com.android.library")
    id ("kotlin-android")
    id ("kotlin-kapt")
}

android {
    configureAndroidLibrary(namespace = "com.example.core")
}


dependencies {

    implementation(Dependencies.ANDROID_CORE)
    implementation(Dependencies.APP_COMPAT)
    implementation(Dependencies.MATERIAL)
    testImplementation(Dependencies.JUNIT)

    //retrofit
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.RETROFIT_CONVERTER_GSON)
    implementation(Dependencies.INTERCEPTOR)

    //dagger
    implementation(Dependencies.DAGGER)
    kapt(Dependencies.DAGGER_COMPILER)

    //navigation
    Dependencies.navigations.forEach(::implementation)

    implementation(Dependencies.ROOM)
    implementation(Dependencies.ROOM_KOTLIN_EXTENSIONS)
    kapt(Dependencies.ROOM_COMPILER)

    implementation(Dependencies.RX)
    implementation(Dependencies.RX_ADAPTER)
}