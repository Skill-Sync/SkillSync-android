package com.ss.skillsync.onboarding.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.ss.skillsync.onboarding.components.SelectedInterestedSkills

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 09/09/2023
 */
@Composable
fun InterestedSkillsPage(
    viewModel: OnboardingViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    ScreenColumn {
        OnboardingTitle(
            header = stringResource(id = R.string.find_your_skills),
            subHeader = stringResource(
                id = R.string.choose_your_interested_skills
            )
        )

        OnboardingSection(
            title = stringResource(R.string.search_for_a_skill),
            modifier = Modifier.fillMaxWidth()
        ) {
            SearchTextField(
                value = state.searchQuery,
                onValueChange = { viewModel.onEvent(OnboardingEvent.SearchQueryChanged(it)) },
                suggestions = state.queryResult,
                onSkillChose = { viewModel.onEvent(OnboardingEvent.SkillSelected(it)) }
            )
        }

        OnboardingSection(
            title = stringResource(R.string.selected_skills),
            modifier = Modifier.fillMaxWidth()
        ) {
            SelectedInterestedSkills(
                selectedSkills = state.selectedInterests,
                onSkillRemoved = { viewModel.onEvent(OnboardingEvent.SkillRemoved(it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 250.dp, max = 500.dp)
                    .border(
                        1.dp,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(12.dp)
            )
        }
        OnboardingButton(
            text = stringResource(id = R.string.next),
            onClick = { viewModel.onEvent(OnboardingEvent.NextClicked) },
            enabled = state.isNextEnabled
        )
    }
}

@Preview
@Composable
fun InterestedSkillsPreview() {
    SkillSyncTheme {
        Box(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            
        }
    }
}