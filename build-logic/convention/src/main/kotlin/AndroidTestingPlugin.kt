import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 03/09/2023
 */
class AndroidTestingPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies.apply {
                add("androidTestImplementation", libs.androidx.test.ext.junit)
                add("androidTestImplementation", libs.espresso.core)
                add("androidTestImplementation", platform(libs.compose.bom))
                add("androidTestImplementation", libs.ui.test.junit4)
            }
        }
    }
}