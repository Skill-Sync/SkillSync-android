import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 03/09/2023
 */
class UnitTestingPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies.apply {
                add("testImplementation", libs.junit)
            }
        }
    }

}