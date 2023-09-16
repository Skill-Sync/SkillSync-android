package com.ss.skillsync.session.making.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.ss.skillsync.commonandroid.components.ScreenColumn
import com.ss.skillsync.commonandroid.components.Section
import com.ss.skillsync.commonandroid.theme.SkillSyncTheme
import com.ss.skillsync.model.MatchResult
import com.ss.skillsync.model.Skill
import com.ss.skillsync.session.making.R
import com.ss.skillsync.session.making.SessionMakingState
import com.ss.skillsync.session.making.SessionMakingUIEvent
import com.ss.skillsync.session.making.component.ActionButtons

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/14/2023.
 */
@Composable
fun MatchSearchingScreen(
    state: SessionMakingState,
    onEvent: (SessionMakingUIEvent) -> Unit,
) {
    BackHandler(true) {
        onEvent(SessionMakingUIEvent.OnStopSearchingClicked)
    }

    ScreenColumn(
        isPausedWhileLoading = false,
        isLoading = state.isSearching
    ) {
        Spacer(modifier = Modifier.weight(0.3f))
        Column(
            Modifier.weight(1f),

            ) {
            SkillSection(skill = state.selectedSkill?.name ?: "adssad")
            Spacer(modifier = Modifier.weight(0.1f))
            SearchingIndicator(
                modifier = Modifier.weight(1f)
            )
            ActionButtons(
                positiveButtonText = null,
                negativeButtonText = stringResource(id = R.string.cancel),
                onNegativeClicked = { onEvent(SessionMakingUIEvent.OnStopSearchingClicked) }
            )
            Spacer(modifier = Modifier.weight(0.1f))
        }
    }
}

@Composable
private fun SkillSection(
    modifier: Modifier = Modifier,
    skill: String,
) {
    Section(
        modifier = modifier,
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = skill,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.labelLarge,
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun SearchingIndicator(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxWidth()
    ) {

    }
}

@Preview
@Composable
fun MatchSearchingPrev() {
    SkillSyncTheme {
        MatchSearchingScreen(
            state = SessionMakingState(
                matchResult = MatchResult(
                    userId = "1",
                    name = "Muhammed Salman",
                    profilePictureUrl = "https://avatars.githubusercontent.com/u/17090794?v=4",
                    matchedSkill = Skill(
                        id = "asd",
                        name = "Android",
                    ),
                ),
                selectedSkill = Skill(
                    "asd",
                    "Android"
                )
            ),
            onEvent = { },
        )
    }
}
