import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidLibraryPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("com.android.library")
                apply("kotlin-android")
                apply("ss.testing")
                apply("ss.lint")
                apply("ss.hilt")
            }

            extensions.configure(BaseExtension::class.java) {
                commonAndroid(project)
            }

            dependencies.apply {
                add("implementation", libs.kotlinx.coroutines.android)
            }
        }
    }
}