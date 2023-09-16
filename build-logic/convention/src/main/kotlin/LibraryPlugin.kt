import org.gradle.api.Plugin
import org.gradle.api.Project

class LibraryPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.plugins.apply {
            apply("kotlin")
            apply("ss.lint")
        }
        project.dependencies.apply {
            add("implementation", project.libs.kotlinx.coroutines.core)
        }
    }
}
