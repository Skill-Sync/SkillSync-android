plugins {
    id("ss.android.library")
}
android {
    namespace = "com.ss.skillsync.core.meeting"
}

dependencies {
    implementation(libs.jitsi)
    implementation(projects.core.meeting.api)
}
