import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 08/09/2023
 */
class NetworkingPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies.apply {
                add("implementation", libs.retrofit)
                add("implementation", libs.okhttp3)
            }
        }
    }
}