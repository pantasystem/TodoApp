import de.undercouch.gradle.tasks.download.org.apache.http.config.ConnectionConfig

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("org.openapi.generator") version "6.2.0"
    id("com.codingfeline.buildkonfig")
    kotlin("plugin.serialization") version "1.7.20"
}
val ktor_version = "2.0.3"
val serialization_version = "1.3.3"
val coroutines_version = "1.6.3"


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
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:$serialization_version")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json")
                api("io.ktor:ktor-client-core:$ktor_version")
                api("io.ktor:ktor-client-serialization:$ktor_version")
                api("io.ktor:ktor-client-content-negotiation:$ktor_version")
                api("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
            }
        }
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
            dependencies {
                api("io.ktor:ktor-client-ios:$ktor_version")
            }
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
    compileSdk = 33
    defaultConfig {
        minSdk = 21
        targetSdk = 33
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

task<Copy>("copyApi") {
    val dirFrom = "$buildDir/kotlin/src/commonMain/kotlin/net/pantasystem/todoapp/api"
    val dirInto = "$projectDir/src/commonMain/kotlin/net/pantasystem/todoapp/api/"
    println("dirInto:$dirInto")
    from(dirFrom)
    into(dirInto)
}

task<Copy>("copyInvoker") {
    val dirFrom = "$buildDir/kotlin/src/commonMain/kotlin/org/openapitools/client"
    val dirInto = "$projectDir/src/commonMain/kotlin/net/pantasystem/todoapp/api/client"
    println("dirInto:$dirInto")
    from(dirFrom)
    into(dirInto)
}

task("buildApi") {
    dependsOn("generate", "copyApi", "copyInvoker")
}
buildkonfig {
    packageName = "net.pantasystem.todoapp"
    // objectName = "YourAwesomeConfig"
    // exposeObjectWithName = "YourAwesomePublicConfig"

    defaultConfigs {
//        buildConfigField(STRING, "name", "value")
        buildConfigField(com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING, "baseUrl", "http://10.0.2.2:8080")
    }
}