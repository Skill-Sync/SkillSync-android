plugins {
    id("ss.lint")
    id("ss.android.library")
}
android {
    namespace = "com.ss.skillsync.core.logging"
}

dependencies {
    implementation(libs.timber)
}