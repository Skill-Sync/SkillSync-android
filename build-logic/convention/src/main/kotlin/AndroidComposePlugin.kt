import com.android.build.gradle.BaseExtension
import com.google.devtools.ksp.gradle.KspExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 8/30/2023.
 */
class AndroidComposePlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("com.google.devtools.ksp")
            }

            extensions.configure(BaseExtension::class.java) {
                configureCompose(project)
            }

            extensions.configure(KspExtension::class.java) {
                arg("compose-destinations.mode", "destinations")
                arg("compose-destinations.moduleName", target.name)
            }

            dependencies.apply {
                add("implementation", platform(libs.compose.bom))
                add("implementation", libs.ui)
                add("implementation", libs.ui.graphics)
                add("implementation", libs.material3)
                add("implementation", libs.ui.tooling.preview)
                add("implementation", libs.androidx.lifecycle.viewmodel.compose)
                add("implementation", libs.composeDestinations)
                add("debugImplementation", libs.ui.tooling)
                add("ksp", libs.composeDestinations.ksp)
            }

        }
    }
}