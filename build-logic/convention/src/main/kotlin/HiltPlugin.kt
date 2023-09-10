import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class HiltPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("kotlin-kapt")
                apply("dagger.hilt.android.plugin")
            }
            dependencies {
                add("implementation",libs.hilt.android)
                add("implementation", libs.hilt.navigation.compose)
                add("kapt",libs.hilt.android.compiler)
            }
        }
    }
}
