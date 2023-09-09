package com.ss.skillsync.onboarding.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ss.skillsync.commonandroid.theme.SkillSyncTheme
import com.ss.skillsync.model.Skill
import com.ss.skillsync.model.SkillLevel

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 09/09/2023
 */
@Composable
fun SelectedStrengths(
    skillsList: List<Skill>,
    onSkillRemoved: (Skill) -> Unit,
    onLevelChange: (Skill, SkillLevel) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(skillsList) { skill ->
            SelectedStrengthItem(skill, onSkillRemoved, onLevelChange)
        }
    }
}

@Composable
fun SelectedStrengthItem(
    skill: Skill,
    onSkillRemoved: (Skill) -> Unit,
    onLevelChanged: (Skill, SkillLevel) -> Unit,
) {
    Row(
        modifier = Modifier
            .height(52.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        SkillName(
            skill = skill,
            onSkillRemoved = onSkillRemoved,
            modifier = Modifier
                .weight(3f)
                .fillMaxHeight()
        )
        SelectLevelDropDown(
            skillLevel = skill.level,
            onLevelChange = { skillLevel -> onLevelChanged(skill, skillLevel) },
            modifier = Modifier
                .weight(2f)
                .fillMaxHeight()
        )
    }
}

@Composable
fun SkillName(
    skill: Skill,
    onSkillRemoved: (Skill) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.background(
            MaterialTheme.colorScheme.surface,
            RoundedCornerShape(9.dp)
        ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = skill.name,
            modifier = Modifier
                .padding(start = 12.dp)
                .weight(10f),
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        IconButton(onClick = { onSkillRemoved(skill) }, modifier.weight(1f)) {
            Icon(
                Icons.Default.Close,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
fun SelectLevelDropDown(
    skillLevel: SkillLevel,
    onLevelChange: (SkillLevel) -> Unit,
    modifier: Modifier = Modifier,
) {
    var isMenuExpanded by remember {
        mutableStateOf(false)
    }
    Box(
        modifier = modifier
            .background(
                MaterialTheme.colorScheme.surface,
                RoundedCornerShape(13.dp)
            )
            .clickable(
                onClick = { isMenuExpanded = true },
                indication = null,
                interactionSource = MutableInteractionSource()
            )
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val rotationState by remember {
                derivedStateOf {
                    if (isMenuExpanded) 180f else 0f
                }
            }
            val rotationAnimation by animateFloatAsState(
                targetValue = rotationState,
                label = "DropDown Arrow rotation"
            )
            Text(
                text = skillLevel.text,
                maxLines = 1,
                modifier = Modifier
                    .padding(start = 12.dp),
                style = MaterialTheme.typography.bodyLarge,
                overflow = TextOverflow.Ellipsis,
            )
            IconButton(
                onClick = { isMenuExpanded = !isMenuExpanded },
            ) {
                Icon(
                    Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.rotate(rotationAnimation)
                )
            }

        }
        DropdownMenu(
            expanded = isMenuExpanded,
            onDismissRequest = { isMenuExpanded = false },
            modifier = Modifier.background(MaterialTheme.colorScheme.surface)
        ) {
            val availableChoices = SkillLevel.values().filter { it != SkillLevel.NOCHOICE }
            availableChoices.forEach { skillLevel ->
                DropdownMenuItem(
                    onClick = {
                        onLevelChange(skillLevel)
                        isMenuExpanded = false
                    },
                    text = {
                        Text(
                            text = skillLevel.text,
                            modifier = Modifier.padding(start = 12.dp)
                        )
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun SelectedStrengthItemPreview() {
    SkillSyncTheme {
        Box(
            modifier = Modifier
                .height(200.dp)
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            SelectedStrengthItem(
                skill = Skill(
                    name = "Android aldsjknujiadsbu asdibadsiu",
                    level = SkillLevel.NOCHOICE
                ),
                onSkillRemoved = {},
                onLevelChanged = { _, _ -> }
            )
        }
    }
}