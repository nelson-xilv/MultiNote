plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.androidKsp) apply false
}
buildscript {
    repositories {
        mavenCentral()
    }
}
true