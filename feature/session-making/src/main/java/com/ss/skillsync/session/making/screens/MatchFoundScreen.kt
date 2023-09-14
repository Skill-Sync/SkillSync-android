package com.ss.skillsync.session.making.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ss.skillsync.commonandroid.components.CircularAsyncImage
import com.ss.skillsync.commonandroid.components.ScreenColumn
import com.ss.skillsync.commonandroid.theme.Black
import com.ss.skillsync.commonandroid.theme.Purple
import com.ss.skillsync.commonandroid.theme.SkillSyncTheme
import com.ss.skillsync.model.MatchResult
import com.ss.skillsync.model.Skill
import com.ss.skillsync.session.making.R
import com.ss.skillsync.session.making.SessionMakingState
import com.ss.skillsync.session.making.SessionMakingUIEvent
import com.ss.skillsync.session.making.component.ActionButtons
import com.ss.skillsync.session.making.component.MatchDetails

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/14/2023.
 */
@Composable
fun MatchFoundScreen(
    state: SessionMakingState,
    onEvent: (SessionMakingUIEvent) -> Unit,
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
            ProfileImagesSection(
                userProfileImage = state.userProfileImage ?: "",
                matchedUserProfileImage = state.matchResult?.profilePictureUrl ?: "",
            )
            Spacer(Modifier.weight(0.1f))

            Box(modifier = Modifier.weight(1f)) {
                MatchedUserDetails(
                    name = state.matchResult?.name ?: "",
                    skill = state.matchResult?.matchedSkill?.name ?: "",
                )
            }
        }
        ActionsSection(
            onAccept = { onEvent(SessionMakingUIEvent.OnAcceptMatchClicked) },
            onReject = { onEvent(SessionMakingUIEvent.OnRejectMatchClicked) },
        )
        Spacer(modifier = Modifier.weight(0.1f))
    }
}

@Composable
private fun ProfileImagesSection(
    modifier: Modifier = Modifier,
    userProfileImage: String,
    matchedUserProfileImage: String,
) {

    Box(
        modifier = modifier
    ) {
        CircularAsyncImage(
            imageUrl = userProfileImage,
            contentDescription = "user profile image",
            size = 100.dp,
            modifier = Modifier.padding(start = 80.dp, top = 70.dp)
        )
        CircularAsyncImage(
            imageUrl = matchedUserProfileImage,
            contentDescription = "matched user profile image",
            size = 140.dp,
        )
    }
}

@Composable
private fun MatchedUserDetails(
    modifier: Modifier = Modifier,
    name: String,
    skill: String,
) {
    MatchDetails(
        name = name,
        skill = skill,
        modifier = modifier
            .border(1.dp, Purple, MaterialTheme.shapes.small)
            .background(Black.copy(alpha = 0.30f))
            .padding(top = 13.dp, bottom = 13.dp, start = 20.dp, end = 20.dp),
    )
}


@Composable
private fun ActionsSection(
    modifier: Modifier = Modifier,
    onAccept: () -> Unit,
    onReject: () -> Unit,
) {
    ActionButtons(
        modifier = modifier,
        positiveButtonText = stringResource(id = R.string.accept),
        negativeButtonText = stringResource(id = R.string.reject),
        onPositiveClicked = onAccept,
        onNegativeClicked = onReject,
    )
}

@Preview
@Composable
private fun ProfileImagePrev() {
    SkillSyncTheme {
        MatchFoundScreen(
            state = SessionMakingState(
                matchResult = MatchResult(
                    userId = "sad",
                    name = "Mohammed Salman",
                    profilePictureUrl = "https://i.imgur.com/3tCzZ9B.jpeg",
                    matchedSkill = Skill(
                        id = "ASDsad",
                        name = "Android"
                    )
                )
            ),
            onEvent = {}
        )
    }
}