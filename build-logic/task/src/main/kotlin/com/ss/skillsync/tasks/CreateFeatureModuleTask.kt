package com.ss.skillsync.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.tasks.TaskAction


/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/6/2023.
 */

abstract class CreateFeatureModuleTask : DefaultTask() {

    companion object {
        private const val FEATURE_PATH = "feature"
        private const val TEMPLATES_PATH = "build-logic/task/src/main/kotlin/com/ss/skillsync/tasks/template"
    }

    @TaskAction
    fun createFeatureModule() {
        val project = project.rootProject
        val featureName = project.properties["featureName"] as String
        val featurePath = "$FEATURE_PATH/${featureName.lowercase()}"

        if (project.file(featurePath).exists()) {
            throw Exception("Feature module already exists")
        }
        project.mkdir(featurePath)

        project.createFeatureModule(featureName, featurePath)

        project.appendFeatureToSettingsFile(featureName)
    }

    private fun Project.createFeatureModule(featureName: String, path: String) {
        val buildTemplate = "$TEMPLATES_PATH/feature_module_template.txt"
        val buildFile = "$path/build.gradle.kts"

        // Create build.gradle.kts file
        val buildTemplateText = file(buildTemplate).readText()
        val replaceWord = "\${featureName}"
        val buildText = buildTemplateText.replace(replaceWord, featureName.lowercase())
        file(buildFile).writeText(buildText)


        createCommonFoldersAndFiles(featureName, path)
        createScreenFile(featureName, path)
        createViewModelFile(featureName, path)
    }

    private fun Project.createScreenFile(featureName: String, path: String) {
        val screenTemplate = "$TEMPLATES_PATH/feature_screen_template.txt"
        val fileName = "${featureName.capitalize()}Screen.kt"
        val screenFile = "$path/src/main/java/com/ss/skillsync/$featureName/$fileName.kt"

        val screenTemplateText = file(screenTemplate).readText()
        val screenText = screenTemplateText.apply {
            replace("\${featureName}", featureName)
            replace("\${featureNameSmall}", featureName.lowercase())
        }

        file(screenFile).writeText(screenText)
    }

    private fun Project.createViewModelFile(featureName: String, path: String) {
        val screenTemplate = "$TEMPLATES_PATH/feature_vm_template.txt"
        val fileName = "${featureName.capitalize()}ViewModel.kt"
        val screenFile = "$path/src/main/java/com/ss/skillsync/$featureName/$fileName.kt"

        val screenTemplateText = file(screenTemplate).readText()
        val screenText = screenTemplateText.let {
            it.replace("\${featureName}", featureName)
            it.replace("\${featureNameSmall}", featureName.lowercase())
        }

        file(screenFile).writeText(screenText)
    }


    private fun Project.createCommonFoldersAndFiles(featureName: String, path: String) {
        val srcFolder = "$path/src/main/java/com/ss/skillsync/${featureName.lowercase()}"
        val gitFile = "$path/.gitignore"

        mkdir(srcFolder)
        file(gitFile).writeText("/build")
    }

    private fun Project.appendFeatureToSettingsFile(featureName: String) {
        val path = "$FEATURE_PATH:$featureName"
        val settingsFile = "settings.gradle.kts"
        val settings = file(settingsFile).readText()
        val newSettings = "$settings\ninclude(\":$path\")"
        file(settingsFile).writeText(newSettings)
    }

    private fun String.capitalize() = this.replaceFirstChar { it.uppercase() }

}
