import com.android.build.gradle.BaseExtension
import org.gradle.api.Project

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/1/2023.
 */


fun BaseExtension.configureCompose(target: Project) {
    configureBuildFeatures()
    target.configureComposeOptions(this)
}

private fun BaseExtension.configureBuildFeatures () {
    buildFeatures.apply {
        compose = true
    }
}

@Suppress("UnstableApiUsage")
private fun Project.configureComposeOptions(
    extension: BaseExtension
) {
    extension.composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
}