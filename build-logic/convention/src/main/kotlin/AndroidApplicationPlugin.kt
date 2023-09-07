import com.android.build.gradle.BaseExtension
import com.ss.skillsync.buildlogic.ApkConfig
import com.ss.skillsync.tasks.CreateFeatureModuleTask
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("com.android.application")
                apply("kotlin-android")
                apply("ss.lint")
            }

            extensions.configure(BaseExtension::class.java) {
                commonAndroid(project)
                configureCompose(project)

                namespace = ApkConfig.APPLICATION_ID
                defaultConfig {
                    vectorDrawables.useSupportLibrary = true
                    applicationId = ApkConfig.APPLICATION_ID
                    versionCode = ApkConfig.VERSION_CODE
                    versionName = ApkConfig.VERSION_NAME
                }

                buildTypes {
                    maybeCreate("staging").apply {
                        isShrinkResources = true
                    }
                    maybeCreate("release").apply {
                        isShrinkResources = true
                    }
                }
            }

            tasks.register("createFeature", CreateFeatureModuleTask::class.java)

        }
    }
}
