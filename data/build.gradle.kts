plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.data"
    compileSdk = 34
}

dependencies {
    implementation(project(":data"))

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // retrofit
    implementation(libs.retrofit)

    // moshi
    implementation(libs.converter.moshi)

    // okhttp
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    // koin
    implementation(libs.koin.android)
}