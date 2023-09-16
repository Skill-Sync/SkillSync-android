pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven {
            url = uri("https://github.com/jitsi/jitsi-maven-repository/raw/master/releases")
        }
        google()
        mavenCentral()
    }
}

rootProject.name = "SkillSync"
include(":app")
include(":core:data")
include(":core:notifications")
include(":core:meeting:api")
include(":core:meeting:impl")
include(":core:domain")
include(":core:model")
include(":core:logging")
include(":feature:signin")
include(":feature:signup")
include(":feature:forgetpassword")
include(":feature:welcome")
include(":feature:profile")
include(":feature:profile:user")
include(":feature:profile:mentor")
include(":feature:home")
include(":feature:session")
include(":feature:onboarding")
include(":feature:payment")
include(":feature:settings")
include(":feature:friends")
include(":feature:chat")
include(":feature:session-making")
include(":core:common-android")
include(":feature:mentorhome")
include(":feature:editprofile")
