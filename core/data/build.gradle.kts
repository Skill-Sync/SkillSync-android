plugins {
    id("ss.android.library")
    id("ss.hilt")
    id("ss.networking")
}

android {
    namespace = "com.ss.skillsync.data"
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
    }
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.model)
    implementation(libs.datastore)
    coreLibraryDesugaring(libs.desugarJdkLibs)
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.model)
    implementation(projects.core.logging)
    implementation(libs.datastore)
    implementation(libs.socketIO)
    implementation(libs.socketIO.coroutines)
}
