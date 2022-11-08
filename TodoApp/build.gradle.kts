plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version("7.3.0").apply(false)
    id("com.android.library").version("7.3.0").apply(false)
    kotlin("android").version("1.7.20").apply(false)
    kotlin("multiplatform").version("1.7.20").apply(false)
    id("com.google.dagger.hilt.android").version("2.44").apply(false)
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.20")
        classpath("com.codingfeline.buildkonfig:buildkonfig-gradle-plugin:0.13.3")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
