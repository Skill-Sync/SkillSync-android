@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("ss.android.library")
    id("ss.android.compose")
    id("ss.android.testing")
    id("ss.testing")
}

android {
    namespace = "com.ss.skillsync.commonandroid"
}

dependencies {
    implementation(libs.lottie)
}