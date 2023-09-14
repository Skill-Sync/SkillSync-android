package com.ss.skillsync.profile.mentor

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ss.skillsync.commonandroid.BaseNavigator
import com.ss.skillsync.commonandroid.components.PrimaryActionBrandButton
import com.ss.skillsync.commonandroid.components.ScreenColumn
import com.ss.skillsync.profile.mentor.component.InformationSection
import com.ss.skillsync.profile.mentor.component.ProfileImage
import com.ss.skillsync.profile.mentor.component.SessionDayPickerSection

@Destination
@Composable
fun MentorProfileScreen(
    snackbarHostState: SnackbarHostState,
    navigator: BaseNavigator,
    viewModel: ProfileViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(key1 = state.isSessionBookedSuccessfully) {
        if (state.isSessionBookedSuccessfully) {
            snackbarHostState.showSnackbar("Session booked successfully")
        }
        if (state.failedToBookSession) {
            snackbarHostState.showSnackbar("Failed to book session, please try again")
        }
    }

    LaunchedEffect(key1 = state.navDestination) {
        when (state.navDestination) {
            ProfileNavDestination.Back -> {
                navigator.popBackStack()
            }

            else -> { return@LaunchedEffect }
        }
    }
    MentorProfileContent(
        state = state,
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun MentorProfileContent(
    state: ProfileState,
    onEvent: (ProfileEvent) -> Unit,
) {
    ScreenColumn(
        isLoading = state.isLoading,
        contentPadding = PaddingValues(0.dp),
        arrangement = Arrangement.Top,
        isScrollable = true,
        isBackDisplayed = true,
        onBackClicked = {
            onEvent(ProfileEvent.OnBackClicked)
        }
    ) {
        if (state.isProfileLoaded.not()) {
            onEvent(ProfileEvent.OnBackClicked)
        }
        ProfileImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            imageUrl = state.imageUrl!!,
        )
        Column(
            Modifier.padding(
                16.dp
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            InformationSection(name = state.name, description = state.about, skill = state.skill)
            Spacer(modifier = Modifier.height(28.dp))
            AnimatedVisibility(visible = state.daySessions != null) {
                SessionDayPickerSection(
                    sessionDays = state.daySessions!!,
                    selectedDay = state.selectedDay,
                    selectedSession = state.selectedSession,
                    onDayPicked = {
                        onEvent(ProfileEvent.OnDayClicked(it))
                    },
                    onSessionPicked = {
                        onEvent(ProfileEvent.OnSessionClicked(it))
                    },
                )
            }
            AnimatedVisibility(visible = state.selectedDay != null) {
                Column {
                    Spacer(modifier = Modifier.height(30.dp))
                    PrimaryActionBrandButton(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(R.string.book_session),
                        onClick = {
                            onEvent(ProfileEvent.OnBookSessionClicked)
                        },
                        enabled = state.isBookSessionEnabled,
                    )
                }
            }
        }

    }
}