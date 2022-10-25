plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("org.openapi.generator") version "6.2.0"

}

kotlin {
    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
        }
    }
    
    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting
        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    namespace = "net.pantasystem.todoapp"
    compileSdk = 32
    defaultConfig {
        minSdk = 21
        targetSdk = 32
    }
}
task<org.openapitools.generator.gradle.plugin.tasks.GenerateTask>("generate") {
    //    generatorName = "kotlin"
    println("$rootDir/../openapi/openapi.yml")
    val generatePackage = "net.pantasystem.todoapp.api"



    inputSpec.set("$rootDir/../openapi/openapi.yml")
    outputDir.set("$buildDir/kotlin")
    apiPackage.set(generatePackage)
    invokerPackage.set("$generatePackage.client")
    modelPackage.set(generatePackage)
    generatorName.set("kotlin")

    library.set("multiplatform")
    additionalProperties.put("useCoroutines", "true")

}
//task buildKotlinClient(type: GenerateTask){
//    generatorName = "kotlin"
//    inputSpec = "$rootDir/../openapi/api.yaml".toString()
//    outputDir = "$buildDir/kotlin".toString()
//    apiPackage = "net.pantasystem.helloopenapi.api"
//    invokerPackage = "net.pantasystem.helloopenapi.client"
//
//    modelPackage = "net.pantasystem.helloopenapi.api"
//    configOptions = [
//        dateLibrary: "java8"
//    ]
//    globalProperties = [
//        modelDocs: "false"
//    ]
//
//    library = "jvm-ktor"
//    additionalProperties.put("useCoroutines", "true")
//}
//
//task copy(type: Copy) {
//    def dirFrom = "$buildDir/kotlin/src/main/kotlin/net/pantasystem/helloopenapi"
//    def dirInto = "$projectDir/src/main/java/net/pantasystem/helloopenapi"
//    println(dirFrom)
//    println(dirInto)
//    doFirst {
//        delete(file("$dirInto/api"))
//    }
////    dependsOn("generate")
//    from(dirFrom)
//    into(dirInto)
//
//
//}
//task copyInvoker(type: Copy) {
//    def dirInto = "$projectDir/src/main/java/org/openapitools/client"
//    from("$buildDir/kotlin/src/main/kotlin/org/openapitools/client")
//    into("$projectDir/src/main/java/org/openapitools/client")
//    doFirst {
//        delete(file(dirInto))
//    }
//}