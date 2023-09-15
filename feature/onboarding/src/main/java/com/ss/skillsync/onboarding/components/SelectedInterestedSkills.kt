package com.ss.skillsync.onboarding.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ss.skillsync.commonandroid.theme.SkillSyncTheme
import com.ss.skillsync.model.Skill

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 09/09/2023
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SelectedInterestedSkills(
    selectedSkills: Set<Skill>,
    onSkillRemoved: (Skill) -> Unit,
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()
    FlowRow(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier.verticalScroll(scrollState),
    ) {
        selectedSkills.forEach { skill ->
            SelectedSkill(skill = skill, onSkillRemoved = onSkillRemoved)
        }
    }
}

@Composable
fun SelectedSkill(
    skill: Skill,
    onSkillRemoved: (Skill) -> Unit,
) {
    ElevatedAssistChip(
        onClick = { },
        label = {
            Text(
                text = skill.name,
                maxLines = 1,
                style = MaterialTheme.typography.bodyLarge
            )
        },
        modifier = Modifier.height(32.dp),
        trailingIcon = {
            IconButton(
                onClick = { onSkillRemoved(skill) },
                modifier = Modifier.size(AssistChipDefaults.IconSize)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = null,
                )
            }
        },
        colors = AssistChipDefaults.assistChipColors(
            disabledContainerColor = MaterialTheme.colorScheme.surface,
            disabledTrailingIconContentColor = MaterialTheme.colorScheme.onSurface,
            disabledLabelColor = MaterialTheme.colorScheme.onSurface,
        ),
        border = AssistChipDefaults.assistChipBorder(
            borderColor = Color.Transparent,
            disabledBorderColor = Color.Transparent,
        ),
        enabled = false
    )
}

@Preview
@Composable
fun ChipPreview() {
    SkillSyncTheme {
        SelectedSkill(skill = Skill(name = "Android"), onSkillRemoved = {})
    }
}

@Preview
@Composable
fun SelectedSkillsPreview() {
    val skillsList = remember {
        mutableStateListOf(
            Skill(name = "Android"),
            Skill(name = "Kotlin"),
            Skill(name = "Java"),
            Skill(name = "Swift"),
            Skill(name = "Objective-C"),
            Skill(name = "C++"),
            Skill(name = "C#"),
            Skill(name = "Python"),
            Skill(name = "JavaScript"),
            Skill(name = "React"),
            Skill(name = "React Native"),
            Skill(name = "Flutter"),
            Skill(name = "Dart"),
            Skill(name = "NodeJS"),
            Skill(name = "PHP"),
            Skill(name = "Ruby"),
            Skill(name = "Go"),
            Skill(name = "C"),
            Skill(name = "Rust"),
            Skill(name = "SQL"),
            Skill(name = "NoSQL"),
            Skill(name = "MongoDB"),
            Skill(name = "Firebase"),
            Skill(name = "AWS"),
            Skill(name = "Azure"),
            Skill(name = "Google Cloud"),
            Skill(name = "Docker"),
            Skill(name = "Kubernetes"),
            Skill(name = "Jenkins"),
            Skill(name = "Git"),
            Skill(name = "Jira"),
            Skill(name = "Confluence"),
            Skill(name = "Sketch"),
            Skill(name = "Figma"),
            Skill(name = "Adobe XD"),
            Skill(name = "Photoshop"),
            Skill(name = "Illustrator"),
            Skill(name = "After Effects"),
            Skill(name = "Premiere Pro"),
            Skill(name = "Final Cut Pro"),
            Skill(name = "Blender"),
            Skill(name = "Maya"),
            Skill(name = "3DS Max"),
            Skill(name = "ZBrush"),
            Skill(name = "Unity"),
            Skill(name = "Unreal Engine"),
            Skill(name = "ARKit"),
            Skill(name = "ARCore"),
            Skill(name = "TensorFlow"),
            Skill(name = "PyTorch"),
            Skill(name = "Keras"),
            Skill(name = "Scikit-Learn"),
            Skill(name = "Pandas"),
            Skill(name = "Numpy"),
            Skill(name = "Matplotlib"),
            Skill(name = "Seaborn"),
            Skill(name = "Plotly"),
            Skill(name = "Tableau"),
            Skill(name = "Power BI"),
            Skill(name = "Excel"),
            Skill(name = "PowerPoint"),
            Skill(name = "Word"),
            Skill(name = "Google Sheets"),
            Skill(name = "Google Slides"),
            Skill(name = "Google Docs"),
            Skill(name = "English"),
            Skill(name = "Arabic"),
            Skill(name = "French"),
            Skill(name = "Spanish"),
            Skill(name = "German"),
            Skill(name = "Italian"),
            Skill(name = "Russian"),
            Skill(name = "Chinese"),
            Skill(name = "Japanese"),
            Skill(name = "Korean"),
            Skill(name = "Portuguese"),
            Skill(name = "Hindi"),
            Skill(name = "Urdu"),
            Skill(name = "Turkish"),
            Skill(name = "Persian"),
            Skill(name = "Indonesian"),
            Skill(name = "Malay"),
            Skill(name = "Vietnamese"),
            Skill(name = "Thai")
        )
    }

    SkillSyncTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(MaterialTheme.colorScheme.background)
        ) {
            SelectedInterestedSkills(
                selectedSkills = skillsList.toMutableSet(),
                onSkillRemoved = { skill ->
                    skillsList.remove(skill)
                },
            )
        }
    }
}