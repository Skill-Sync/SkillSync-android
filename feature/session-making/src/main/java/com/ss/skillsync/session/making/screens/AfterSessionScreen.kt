package com.ss.skillsync.session.making.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ss.skillsync.commonandroid.components.CircularAsyncImage
import com.ss.skillsync.commonandroid.components.ScreenColumn
import com.ss.skillsync.commonandroid.theme.SkillSyncTheme
import com.ss.skillsync.model.Skill
import com.ss.skillsync.session.making.R
import com.ss.skillsync.session.making.SessionMakingEvent
import com.ss.skillsync.session.making.SessionMakingState
import com.ss.skillsync.session.making.component.ActionButtons
import com.ss.skillsync.session.making.component.MatchDetails

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/14/2023.
 */

@Composable
fun AfterSessionScreen(
    state: SessionMakingState,
    onEvent: (SessionMakingEvent) -> Unit,
) {
    ScreenColumn(
        arrangement = Arrangement.Top,
    ) {
        Spacer(modifier = Modifier.weight(0.3f))
        Column(
            Modifier.weight(1f),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
        ) {
            CircularAsyncImage(
                imageUrl = state.matchResult?.profilePictureUrl ?: "",
                contentDescription = "Matched User Profile Image",
                size = 180.dp
            )
            Spacer(Modifier.weight(0.1f))
            Box(modifier = Modifier.weight(1f)) {
                MatchDetails(
                    name = state.matchResult?.name ?: "",
                    skill = state.matchResult?.matchedSkill?.name ?: "",
                )
            }
        }
        ActionButtons(
            positiveButtonText = stringResource(R.string.add_to_friends),
            negativeButtonText = stringResource(R.string.cancel),
            onPositiveClicked = { onEvent(SessionMakingEvent.OnAddToFriendsClicked) },
            onNegativeClicked = { onEvent(SessionMakingEvent.OnLeaveSessionMaking) },
        )
        Spacer(modifier = Modifier.weight(0.1f))
    }
}

@Preview
@Composable
fun AfterSessionPrev() {
    SkillSyncTheme {
        AfterSessionScreen(
            state = SessionMakingState(
                matchResult = com.ss.skillsync.model.MatchResult(
                    userId = "1",
                    name = "Muhammed Salman",
                    profilePictureUrl = "https://avatars.githubusercontent.com/u/17090794?v=4",
                    matchedSkill = Skill(
                        id = "asd",
                        name = "Android",
                    ),
                )
            ),
            onEvent = {  },
        )
    }
}