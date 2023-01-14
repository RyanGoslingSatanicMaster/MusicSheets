import org.gradle.api.artifacts.dsl.DependencyHandler

object AppDependencies {

    const val kotlinDep = "androidx.core:core-ktx:${Versions.kotlinVersion}"
    const val composeDep = "androidx.compose.ui:ui:${Versions.composeUIVersion}"
    const val lifecycleDep = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleVersion}"
    const val lifecycleLiveDataDep = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleVersion}"
    const val lifecycleViewModelDep = "androidx.lifecycle:lifecycle-viewmodel-ktx${Versions.lifecycleVersion}"
    const val lifecycleServiceDep = "androidx.lifecycle:lifecycle-service-ktx:${Versions.lifecycleVersion}"
    const val dagger2Dep = "com.google.dagger:dagger:${Versions.dagger2Version}"
    const val retrofitDep = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    const val retrofitGsonDep = "com.squareup.retrofit2:converter-gson:${Versions.dagger2Version}"

    const val dagger2CompilerDep = "com.google.dagger:dagger-compiler:${Versions.dagger2Version}"

    const val jUnitDep = "junit:junit:${Versions.jUnitVersion}"

    const val androidJUnitDep = "androidx.test.ext:junit:${Versions.androidJunit}"
    const val composePreviewDep = "androidx.compose.ui:ui-tooling-preview:${Versions.composeUIVersion}"
    const val composeJunitTestDep = "androidx.compose.ui:ui-test-junit4:${Versions.composeUIVersion}"

    const val composeToolsDep = "androidx.compose.ui:ui-tooling:${Versions.composeUIVersion}"
    const val composeManifestTestDep = "androidx.compose.ui:ui-test-manifest:${Versions.composeUIVersion}"


    val appLibraries = arrayListOf<String>().apply {
        add(kotlinDep)
        add(composeDep)
        add(composePreviewDep)
        add(lifecycleDep)
        add(lifecycleLiveDataDep)
        add(lifecycleServiceDep)
        add(lifecycleViewModelDep)
        add(dagger2Dep)
        add(retrofitDep)
        add(retrofitGsonDep)
    }

    val kaptLibraries = arrayListOf<String>().apply {
        add(dagger2CompilerDep)
    }

    val androidTestLibraries = arrayListOf<String>().apply {
        add(androidJUnitDep)
        add(composeJunitTestDep)
    }

    val testLibraries = arrayListOf<String>().apply {
        add(jUnitDep)
    }

    val debugLibraries = arrayListOf<String>().apply {
        add(composeToolsDep)
        add(composeManifestTestDep)
    }
}

    //util functions for adding the different type dependencies from build.gradle file
    fun DependencyHandler.kapt(list: List<String>) {
        list.forEach { dependency ->
            add("kapt", dependency)
        }
    }

    fun DependencyHandler.kapt(dep: String) {
        add("kapt", dep)
    }

    fun DependencyHandler.implementation(list: List<String>) {
        list.forEach { dependency ->
            add("implementation", dependency)
        }
    }

    fun DependencyHandler.androidTestImplementation(list: List<String>) {
        list.forEach { dependency ->
            add("androidTestImplementation", dependency)
        }
    }

    fun DependencyHandler.testImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("testImplementation", dependency)
    }
}
