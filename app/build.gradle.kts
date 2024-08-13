plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.serialization)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.room.compiler)
}

android {
    namespace = "com.example.jakala_swapi"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.jakala_swapi"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        buildConfigField("String", "BASE_URL", "\"https://swapi.dev/\"")
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

    room {
        schemaDirectory("$projectDir/schemas")
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    /* Di:Hilt */
    implementation(libs.hilt)
    ksp(libs.hilt.compiler)
    /* Di:Hilt Navigation Compose */
    implementation(libs.hilt.navigation.compose)
    /* Navigation Compose */
    implementation(libs.navigation.compose)
    /* Net:Retrofit */
    implementation(libs.retrofit)
    /* Net:Kotlinx Serialization and Converter */
    implementation(libs.kotlinx.serialization)
    implementation(libs.serialization.converter)
    /* Net:OkHttp*/
    implementation(libs.okHttp)
    implementation(libs.okhttp.logging)
    implementation(libs.lifecycle.compose)
    /* Db:Room*/
    implementation(libs.room)
    implementation(libs.room.ktx)
    ksp(libs.room.annotation)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}