plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    namespace = "com.example.feature_handle_editor"
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk

        testInstrumentationRunner = AppConfig.androidTestInstrumentation
        vectorDrawables {
            useSupportLibrary = true
        }
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
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.extComposeKotlinVersion
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        compose = true
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(project(":core-sensor"))
    implementation(project(":common"))
    implementation(project(":core-synth"))
    implementation(AppDependencies.androidDep)
    implementation(AppDependencies.composeActivityDep)
    implementation(AppDependencies.composeDep)
    implementation(AppDependencies.composeMaterialDep)
    implementation(AppDependencies.composePreviewDep)
    implementation(AppDependencies.kotlinDep)
    implementation(AppDependencies.dagger2Dep)
    implementation(AppDependencies.lifecycleDep)
    implementation(AppDependencies.lifecycleViewModelDep)
    implementation(AppDependencies.lifecycleLiveDataDep)
    implementation("androidx.appcompat:appcompat:1.6.0")
    implementation("com.google.android.material:material:1.4.0")
    testImplementation(AppDependencies.jUnitDep)
    kapt(AppDependencies.dagger2CompilerDep)
}
