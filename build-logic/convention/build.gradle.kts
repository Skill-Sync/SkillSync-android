import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.ss.skillsync.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)

    implementation(libs.ktlint.gradle)
    implementation(libs.detekt.gradle)

    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
    implementation(project(":task"))
    compileOnly(libs.ksp.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("skillSyncAndroidApplication") {
            id = "ss.android.application"
            implementationClass = "AndroidApplicationPlugin"
        }
        register("skillSyncAndroidFeature") {
            id = "ss.android.feature"
            implementationClass = "AndroidFeaturePlugin"
        }
        register("skillSyncHilt") {
            id = "ss.hilt"
            implementationClass = "HiltPlugin"
        }
        register("skillSyncAndroidLibrary") {
            id = "ss.android.library"
            implementationClass = "AndroidLibraryPlugin"
        }
        register("skillSyncLibrary") {
            id = "ss.library"
            implementationClass = "LibraryPlugin"
        }
        register("skillSyncLint") {
            id = "ss.lint"
            implementationClass = "LintPlugin"
        }
        register("skillSyncAndroidCompose") {
            id = "ss.android.compose"
            implementationClass = "AndroidComposePlugin"
        }
        register("skillSyncUnitTesting") {
            id = "ss.testing"
            implementationClass = "UnitTestingPlugin"
        }
        register("skillSyncAndroidTesting") {
            id = "ss.android.testing"
            implementationClass = "AndroidTestingPlugin"
        }
        register("skillSyncNetworking") {
            id = "ss.networking"
            implementationClass = "NetworkingPlugin"
        }
        register("skillSyncCoil") {
            id = "ss.coil"
            implementationClass = "CoilPlugin"
        }
    }
}
