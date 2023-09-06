import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidFeaturePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("com.android.library")
                apply("kotlin-android")
                apply("ss.hilt")
                apply("ss.android.compose")
                apply("ss.android.testing")
                apply("ss.testing")
            }
            extensions.configure(BaseExtension::class.java) {
                commonAndroid(target)
            }

            dependencies.apply {
                add("implementation", project(":core:model"))
                add("implementation", project(":core:domain"))
                add("implementation", project(":core:common-android"))
            }

        }
    }
}
