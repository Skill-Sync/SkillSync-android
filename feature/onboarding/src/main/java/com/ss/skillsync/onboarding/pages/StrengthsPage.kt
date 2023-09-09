package com.ss.skillsync.onboarding.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ss.skillsync.commonandroid.components.ScreenColumn
import com.ss.skillsync.commonandroid.theme.SkillSyncTheme
import com.ss.skillsync.onboarding.OnboardingEvent
import com.ss.skillsync.onboarding.OnboardingViewModel
import com.ss.skillsync.onboarding.R
import com.ss.skillsync.onboarding.components.OnboardingButton
import com.ss.skillsync.onboarding.components.OnboardingSection
import com.ss.skillsync.onboarding.components.OnboardingTitle
import com.ss.skillsync.onboarding.components.SearchTextField
import com.ss.skillsync.onboarding.components.SelectedStrengths

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 09/09/2023
 */
@Composable
fun StrengthsPage(
    viewModel: OnboardingViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    ScreenColumn {
        OnboardingTitle(
            header = stringResource(id = R.string.find_your_skills),
            subHeader = stringResource(id = R.string.select_your_strengths)
        )

        OnboardingSection(title = stringResource(R.string.search_for_a_skill)) {
            SearchTextField(
                value = state.searchQuery,
                onValueChange = {
                    viewModel.onEvent(
                        OnboardingEvent.SearchQueryChanged(it)
                    )
                },
                suggestions = state.queryResult,
                onSkillChose = { viewModel.onEvent(OnboardingEvent.SkillSelected(it)) },
            )
        }

        OnboardingSection(title = stringResource(R.string.selected_skills)) {
            SelectedStrengths(
                skillsList = state.selectedStrengths.toList(),
                onSkillRemoved = { viewModel.onEvent(OnboardingEvent.SkillRemoved(it)) },
                onLevelChange = { skill, skillLevel ->
                    viewModel.onEvent(OnboardingEvent.SkillLevelUpdated(skill, skillLevel))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 250.dp, max = 500.dp)
            )
        }

        OnboardingButton(
            text = stringResource(R.string.done),
            onClick = { viewModel.onEvent(OnboardingEvent.DoneClicked) },
            enabled = state.isDoneEnabled
        )
    }
}

@Preview
@Composable
fun StrengthsPreview() {
    SkillSyncTheme {
        Box(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            StrengthsPage()
            IconButton(
                onClick = { },
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(top = 16.dp, start = 16.dp)
            ) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}