import org.gradle.api.JavaVersion

object AppConfig {
    const val packageName = "com.chefio"
    const val compileSdkVersion = 33
    const val buildToolsVersion = "33.0.0"
    const val minSdkVersion = 24
    const val targetSdkVersion = 33


    const val versionCode = 1
    const val versionName = "1.0"

    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    const val jvmTarget = "1.8"
    val sourceCompatibility = JavaVersion.VERSION_1_8
    val targetCompatibility = JavaVersion.VERSION_1_8

    const val kotlinSourceDirectory = "src/main/kotlin"
}