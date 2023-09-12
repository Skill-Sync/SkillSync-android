import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

val userEmail: String = gradleLocalProperties(rootDir).getProperty("user.email")
val userPass: String = gradleLocalProperties(rootDir).getProperty("user.pass")

plugins {
    id("ss.android.library")
    id("ss.hilt")
}
android {
    namespace = "com.ss.skillsync.domain"
    buildTypes {
        getByName("debug") {
            buildConfigField("String", "userEmail", userEmail)
            buildConfigField("String", "userPass", userPass)
        }
    }
}

dependencies {
    implementation(projects.core.model)
    implementation(libs.kotlinx.coroutines.core)
}
