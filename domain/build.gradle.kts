plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.domain"
    compileSdk = 34
}

dependencies {
    implementation(project(":domain"))

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // koin
    implementation(libs.koin.android)

    // moshi
    implementation(libs.converter.moshi)
}