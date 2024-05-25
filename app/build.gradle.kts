plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.example.githubsearchapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.githubsearchapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        multiDexEnabled = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    implementation(project(":datas"))
    implementation(project(":domains"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.kotlin.stdlib.jdk8)

    // app compat
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    // ui
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // koin
    implementation(libs.koin.android)

    // coroutine
    implementation(libs.kotlinx.coroutines.core)
    kapt(libs.kotlinx.metadata.jvm)
    implementation(libs.kotlinx.coroutines.android)

    // glide
    implementation(libs.glide)
//    kapt(libs.compiler)

    // room
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.runtime)
    kapt(libs.androidx.room.compiler)

    // retrofit
    implementation(libs.retrofit)

    // moshi
    implementation(libs.converter.moshi)

    // chucker
    debugImplementation(libs.library)
    releaseImplementation(libs.library.no.op)

    // lifecycle
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.common.java8)
    implementation(libs.androidx.lifecycle.viewmodel.savedstate)

    implementation(libs.kotlin.stdlib)

}