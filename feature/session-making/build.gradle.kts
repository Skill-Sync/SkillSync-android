plugins {
    id("ss.android.feature")
}
android {
    namespace = "com.ss.skillsync.session.making"
}

dependencies {
    implementation(projects.core.meeting.api)
}
