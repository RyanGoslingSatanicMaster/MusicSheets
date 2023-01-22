plugins {
    id("com.android.library")
    kotlin("kapt")
    kotlin("android")
}

android {
    namespace = "com.example.feature_motion_music"
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
        getByName("release") {
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
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":common"))
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
    testImplementation(AppDependencies.jUnitDep)
    kapt(AppDependencies.dagger2CompilerDep)
}
