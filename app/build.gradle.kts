plugins {
//    alias(libs.plugins.android.application)
//    alias(libs.plugins.jetbrains.kotlin.android)
//    alias(libs.plugins.com.google.dagger.hilt.android)
//    alias(libs.plugins.com.google.devtools.ksp)
//    alias(libs.plugins.org.jetbrains.kotlin.serialization)
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")

}

android {
    namespace = "com.mjapp.rush"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.mjapp.rush"
        minSdk = 35
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
//    implementation(libs.androidx.core.ktx)
//    implementation(libs.androidx.lifecycle.runtime.ktx)
//    implementation(libs.androidx.activity.compose)
//    implementation(platform(libs.androidx.compose.bom))
//    implementation(libs.androidx.ui)
//    implementation(libs.androidx.ui.graphics)
//    implementation(libs.androidx.ui.tooling.preview)
//    implementation(libs.androidx.material3)
//    testImplementation(libs.junit)
//    androidTestImplementation(libs.androidx.junit)
//    androidTestImplementation(libs.androidx.espresso.core)
//    androidTestImplementation(platform(libs.androidx.compose.bom))
//    androidTestImplementation(libs.androidx.ui.test.junit4)
//    debugImplementation(libs.androidx.ui.tooling)
//    debugImplementation(libs.androidx.ui.test.manifest)
//
//    implementation(libs.lifecycle.runtime.compose)
//    implementation(libs.lifecycle.viewmodel.compose)
//    implementation(libs.coil.compose)
//
//    implementation(libs.ui)
//    implementation(libs.hilt.android)
//    implementation(libs.hilt.navigation.compose)
//    implementation(libs.navigation)
//    implementation(libs.room.runtime)
//    implementation(libs.room.ktx)
//
//    implementation(libs.retrofit2)
//    implementation(libs.converter.gson)
//    implementation(libs.converter.moshi)
//    implementation(libs.interceptor)
//    implementation(libs.retrofit.adapter)
//    implementation(libs.retrofit.kotlin.serialization)
//    implementation(libs.kotlin.serialization)
//
//    implementation(libs.room.paging)
//    implementation(libs.paging.runtime)
//    implementation(libs.paging.compose)
//
//    implementation(libs.multidex)
//    implementation(libs.swipe.refresh)
//    implementation(libs.glide)
//    implementation(libs.extended.icon)
//    ksp(libs.room.compiler)
//    ksp(libs.hilt.android.compiler)
//    ksp(libs.hilt.compose)
    implementation("androidx.core:core-ktx:1.16.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.0-alpha01")
    implementation("androidx.activity:activity-compose:1.9.0-alpha01")
    implementation(platform("androidx.compose:compose-bom:2024.04.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // Retrofit for networking
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // Room for local persistence
    implementation("androidx.room:room-runtime:2.7.0")
    kapt("androidx.room:room-compiler:2.7.0")
    implementation("androidx.room:room-ktx:2.7.0") // For coroutines support

    // Kotlin Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.0-RC")

    // Hilt for dependency injection
    implementation("com.google.dagger:hilt-android:2.48.1")
    kapt("com.google.dagger:hilt-android-compiler:2.48.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    // Coil for image loading
    implementation("io.coil-kt:coil-compose:2.4.0")


}