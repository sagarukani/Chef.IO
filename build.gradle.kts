buildscript {
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.44")
    }
}

plugins {
    id("com.android.application") version("7.1.2") apply(false)
    id("com.android.library") version("7.1.1") apply(false)
    id("org.jetbrains.kotlin.android") version("1.6.10") apply(false)
//    id("com.google.gms.google-services") version("4.3.10") apply(false)
//    id("com.google.firebase.crashlytics") version("2.8.1") apply(false)
    id("com.google.protobuf") version("0.8.17") apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}