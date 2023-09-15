package com.ss.skillsync.onboarding.pages

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ss.skillsync.commonandroid.components.ScreenColumn
import com.ss.skillsync.commonandroid.theme.SemiBlack
import com.ss.skillsync.commonandroid.theme.SkillSyncTheme
import com.ss.skillsync.onboarding.OnboardingEvent
import com.ss.skillsync.onboarding.OnboardingState
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
    state: OnboardingState,
    onEvent: (OnboardingEvent) -> Unit,
) {
    ScreenColumn(
        screenColor = SemiBlack,
        isBackDisplayed = state.isBackVisible,
        onBackClicked = { onEvent(OnboardingEvent.BackClicked) }
    ) {
        OnboardingTitle(
            header = stringResource(id = R.string.find_your_skills),
            subHeader = stringResource(id = R.string.select_your_strengths)
        )
        OnboardingSection(title = stringResource(R.string.search_for_a_skill)) {
            SearchTextField(
                modifier = Modifier.heightIn(min = 70.dp, max = 200.dp),
                value = state.searchQuery,
                onValueChange = {
                    onEvent(
                        OnboardingEvent.SearchQueryChanged(it)
                    )
                },
                suggestions = state.queryResult,
                onSkillChose = { onEvent(OnboardingEvent.SkillSelected(it)) },
            )
        }

        OnboardingSection(title = stringResource(R.string.selected_skills)) {
            SelectedStrengths(
                skillsList = state.selectedStrengths.toList(),
                onSkillRemoved = { onEvent(OnboardingEvent.SkillRemoved(it)) },
                onLevelChange = { skill, skillLevel ->
                    onEvent(OnboardingEvent.SkillLevelUpdated(skill, skillLevel))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 250.dp, max = 400.dp)
            )
        }

        OnboardingButton(
            text = stringResource(R.string.done),
            onClick = { onEvent(OnboardingEvent.DoneClicked) },
            enabled = state.isDoneEnabled
        )
    }
}

@Preview
@Composable
fun StrengthsPreview() {
    SkillSyncTheme {
        StrengthsPage(
            state = OnboardingState(
                currentPageIndex = 1,
            ),
            onEvent = { }
        )
    }
}