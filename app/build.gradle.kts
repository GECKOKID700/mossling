plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.axton.mossling"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.axton.mossling"
        minSdk = 26
        targetSdk = 34
        versionCode = 2
        versionName = "1.1"
    }

    // One permanent key, so every new build installs over the old one and keeps your progress
    signingConfigs {
        create("mossling") {
            storeFile = file("mossling.keystore")
            storePassword = "mossling123"
            keyAlias = "mossling"
            keyPassword = "mossling123"
        }
    }

    buildTypes {
        debug {
            signingConfig = signingConfigs.getByName("mossling")
        }
        release {
            signingConfig = signingConfigs.getByName("mossling")
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}
