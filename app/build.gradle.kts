@Suppress("DSL_SCOPE_VIOLATION") // Remove once KTIJ-19369 is fixed
plugins {
    id("ss.android.application")
    id("ss.hilt")
    id("ss.android.compose")

    // TODO Move to [logger module]
    alias(libs.plugins.google.services)
    alias(libs.plugins.firebase.crashlytics)
}

android {
    signingConfigs {
        maybeCreate("staging").apply {
            keyAlias = "key1"
            keyPassword = "staging007"
            storeFile = file("keystore/staging.jks")
            storePassword = "staging007"
        }
    }

    buildTypes {
        buildTypes {
            maybeCreate("debug").apply {
                resValue("bool", "FIREBASE_CRASHLYTICS_ENABLED", "false")
            }
            maybeCreate("staging").apply {
                signingConfig = signingConfigs.getByName("staging")
                resValue("bool", "FIREBASE_CRASHLYTICS_ENABLED", "true")
            }
            maybeCreate("release").apply {
                resValue("bool", "FIREBASE_CRASHLYTICS_ENABLED", "true")
            }
        }
    }
}

dependencies {

    implementation(projects.core.commonAndroid)
    implementation(projects.core.domain)
    implementation(projects.core.data)
    implementation(projects.core.model)
    implementation(projects.core.notifications)

    implementation(projects.feature.home)
    implementation(projects.feature.forgetpassword)
    implementation(projects.feature.chat)
    implementation(projects.feature.onboarding)
    implementation(projects.feature.friends)
    implementation(projects.feature.payment)
    implementation(projects.feature.profile.mentor)
    implementation(projects.feature.profile.user)
    implementation(projects.feature.session)
    implementation(projects.feature.sessionMaking)
    implementation(projects.feature.settings)
    implementation(projects.feature.signin)
    implementation(projects.feature.signup)
    implementation(projects.feature.welcome)

    implementation(libs.activity.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // Firebase services
    // TODO move to [logger module]
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.crashlytics.ktx)

    // Logging
    implementation(libs.timber)

    // Splash screen API
    implementation(libs.androidx.core.splashscreen)
}
