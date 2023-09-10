import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 10/09/2023
 */
class CoilPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies.apply {
                add("implementation", libs.coil)
                add("implementation", libs.coil.compose)
            }
        }
    }
}