plugins {
    id("com.android.application")
    kotlin("kapt")
    kotlin("android")
}

android {
    namespace = "com.example.musicsheets"
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        applicationId = "com.example.musicsheets"
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.extComposeKotlinVersion
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}

dependencies {
    implementation(project(":feature-motion-music"))
    implementation(project(":core-sensor"))
    implementation(project(":core-synth"))
    implementation(project(":common"))
    implementation(AppDependencies.androidDep)
    implementation(AppDependencies.composeDep)
    implementation(AppDependencies.composePreviewDep)
    implementation(AppDependencies.kotlinDep)
    implementation(AppDependencies.dagger2Dep)
    implementation(AppDependencies.lifecycleDep)
    implementation(AppDependencies.lifecycleViewModelDep)
    implementation(AppDependencies.lifecycleLiveDataDep)
    implementation(AppDependencies.composeActivityDep)
    implementation(AppDependencies.composeMaterialDep)
    testImplementation(AppDependencies.jUnitDep)
    debugImplementation(AppDependencies.composeToolsDep)
    debugImplementation(AppDependencies.composeManifestTestDep)
    kapt(AppDependencies.dagger2CompilerDep)
}
