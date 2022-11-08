plugins {
    id("com.android.application")
    kotlin("android")
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
}

android {
    namespace = "net.pantasystem.todoapp.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "net.pantasystem.todoapp.android"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(":shared"))
    implementation("androidx.compose.ui:ui:1.2.1")
    implementation("androidx.compose.ui:ui-tooling:1.2.1")
    implementation("androidx.compose.ui:ui-tooling-preview:1.2.1")
    implementation("androidx.compose.foundation:foundation:1.2.1")
    implementation("androidx.compose.material:material:1.2.1")
    implementation("androidx.activity:activity-compose:1.5.1")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-compiler:2.44")

    implementation("androidx.collection:collection-ktx:1.2.0")
    implementation("androidx.fragment:fragment-ktx:1.5.4")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.0-alpha03")
    implementation("androidx.palette:palette-ktx:1.0.0")

    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.0-alpha03")

    implementation("androidx.navigation:navigation-compose:2.5.3")
    implementation("io.ktor:ktor-client-cio:2.1.2")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.6.10")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    implementation("androidx.compose.material:material-icons-extended:1.3.0")

}