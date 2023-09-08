plugins {
    id("ss.android.library")
    id("ss.hilt")
}
android {
    namespace = "com.ss.skillsync.domain"
}

dependencies {
    implementation(projects.core.model)
    implementation(libs.kotlinx.coroutines.core)
}
