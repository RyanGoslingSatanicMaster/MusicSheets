import plugins.activity_search.ActivitySearchPlugin

buildscript {
    repositories {
        google()
        mavenCentral()
        maven { url = java.net.URI("https://jitpack.io") }
    }
}

plugins {
    id("com.android.application") version "7.3.1" apply false
    id("com.android.library") version "7.3.1" apply false
    id("org.jetbrains.kotlin.android") version "1.6.10" apply false
    kotlin("kapt") version "1.7.20" apply false
}
apply<ActivitySearchPlugin>()




