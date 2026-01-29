plugins {
    alias(libs.plugins.android.application)
    // REMOVE: alias(libs.plugins.kotlin.android) - It's now built-in to AGP 9.0
}

android {
    namespace = "com.example.studentsapp"

    // Corrected AGP 9.0 syntax for API 36
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.studentsapp"
        minSdk = 24
        targetSdk = 36 // Explicitly set this to match compileSdk
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
        // AGP 9.0 requires Java 17 for builds
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    

    // Ensure ViewBinding or DataBinding is enabled if you are using them
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}