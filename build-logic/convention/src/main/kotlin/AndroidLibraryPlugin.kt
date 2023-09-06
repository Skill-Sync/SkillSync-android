import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidLibraryPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("com.android.library")
                apply("kotlin-android")
                apply("ss.lint")
            }

            extensions.configure(BaseExtension::class.java) {
                commonAndroid(project)
            }

        }
    }
}