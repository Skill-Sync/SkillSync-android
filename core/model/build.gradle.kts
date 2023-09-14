plugins {
    id("ss.library")
    kotlin("plugin.serialization")
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
}
