import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jlleitschuh.gradle.ktlint.KtlintExtension

class LintPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("org.jlleitschuh.gradle.ktlint")
                apply("io.gitlab.arturbosch.detekt")
            }
            extensions.configure(KtlintExtension::class.java) {
                version.set(libs.versions.ktlint.runtime)
                android.set(true)
                verbose.set(true)
                outputToConsole.set(true)
                ignoreFailures.set(false)
                enableExperimentalRules.set(true)
                filter {
                    exclude("**/generated/**")
                    include("**/kotlin/**")
                }
            }

            extensions.configure(DetektExtension::class.java) {
                config.setFrom(rootProject.files("config/detekt/detekt.yml"))
            }
        }
    }
}
