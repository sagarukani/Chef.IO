import com.google.protobuf.gradle.builtins
import com.google.protobuf.gradle.generateProtoTasks
import com.google.protobuf.gradle.protobuf
import com.google.protobuf.gradle.protoc

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
//    id("com.google.gms.google-services")
//    id("com.google.firebase.crashlytics")
    id("com.google.protobuf")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = AppConfig.compileSdkVersion
    buildToolsVersion = AppConfig.buildToolsVersion

    buildFeatures {
        dataBinding = true
    }

    defaultConfig {
        applicationId = AppConfig.packageName
        minSdk = AppConfig.minSdkVersion
        targetSdk = AppConfig.targetSdkVersion
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName
        testInstrumentationRunner = AppConfig.testInstrumentationRunner
        multiDexEnabled = true
        //Change your API url here
        buildConfigField("String", "BaseUrl", "\"https://5394-2607-fea8-1d5e-1300-1537-386f-5c0d-b159.ngrok-free.app/api/\"")
        buildConfigField("String", "PhotosUrl", "\"https://5394-2607-fea8-1d5e-1300-1537-386f-5c0d-b159.ngrok-free.app/uploads/\"")

        kapt {
            arguments {
                arg("room.schemaLocation", "$projectDir/schemas")
            }
        }
    }

    signingConfigs {
        create("release") {
            keyAlias = AppSigningConfig.keyAlias
            keyPassword = AppSigningConfig.keyPassword
            storeFile = file(AppSigningConfig.storeFile)
            storePassword = AppSigningConfig.storePassword
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("release")
        }
    }

    compileOptions {
        sourceCompatibility = AppConfig.sourceCompatibility
        targetCompatibility = AppConfig.targetCompatibility
    }

    kotlinOptions {
        jvmTarget = AppConfig.jvmTarget
    }
}

dependencies {
    api(Libs.kotlin)
    api(Libs.coroutineCore)
    api(Libs.coroutineAndroid)

    api(Libs.multidex)
    api(Libs.lifecycleExtension)
    api(Libs.appCompat)
    api(Libs.androidCore)
    api(Libs.constraintLayout)
    api(Libs.recyclerView)
    api(Libs.materialDesign)

    api(Libs.navigationUi)
    api(Libs.navigationFragment)
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    api(Libs.spd)

    testApi(Libs.junit)
    androidTestApi(Libs.junitExt)
    androidTestApi(Libs.expressoCore)

    api(Libs.timber)
    api(Libs.securityCrypto)
    api(Libs.preference)
//    api(Libs.analytics)

    // Networking
    api(Libs.okHttp)
    api(Libs.loggingInterceptor)
    api(Libs.retrofit)
    api(Libs.gsonConverter)
    api(Libs.scalarConverter)

    // Coil
    api(Libs.coil)
    api(Libs.coilVideo)

    // Room Database
    api(Libs.room)
    api(Libs.roomRuntime)
    kapt(Libs.roomCompiler)
    testApi(Libs.roomTesting)

    // Datastore
    api(Libs.dataStore)
    api(Libs.preferenceDataStore)
    api(Libs.javaLite)

    implementation(Libs.hiltAndroid)
    kapt(Libs.hiltCompiler)

    implementation(Libs.cpp)
    implementation(Libs.imageView)
    implementation(Libs.rating)
    implementation("com.github.KevinSchildhorn:OTPView:0.2.5")

    implementation("com.github.bumptech.glide:glide:4.15.1")
    annotationProcessor("com.github.bumptech.glide:compiler:4.14.2")
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.18.0"
    }

    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                create("java") { option("lite") }
            }
        }
    }
}
