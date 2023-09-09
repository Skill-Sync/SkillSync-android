plugins {
    id("ss.android.feature")
}
android {
    namespace = "com.ss.skillsync.signin"
}

dependencies {
    implementation(projects.core.meeting.api)
}
