plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.androidKsp)
}

android {
    namespace = "com.nelsonxilv.notesapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.nelsonxilv.notesapp"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.bundles.androidx)
    implementation(libs.material)

    implementation(libs.circle.imageview)
    implementation(libs.bundles.coroutines)

    implementation(libs.bundles.room)
    ksp(libs.room.compiler)

    implementation(libs.bundles.navigation)
    implementation(libs.bundles.retrofit)

    testImplementation(libs.junit)

    androidTestImplementation(libs.bundles.androidx.test)
}