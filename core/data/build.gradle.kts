plugins {
    id("ss.android.library")
    id("ss.hilt")
    id("ss.networking")
}

android {
    namespace = "com.ss.skillsync.data"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.model)
    implementation(libs.datastore)
}
