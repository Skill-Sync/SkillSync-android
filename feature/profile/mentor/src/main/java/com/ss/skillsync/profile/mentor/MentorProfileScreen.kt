package com.ss.skillsync.profile.mentor

import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ss.skillsync.commonandroid.components.CircularAsyncImage
import com.ss.skillsync.commonandroid.components.ScreenColumn

@Destination
@Composable
fun MentorProfileScreen(
    snackbarHostState: SnackbarHostState,
    viewModel: ProfileViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
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
        isLoading = state.isLoading
    ) {
        if (state.isProfileLoaded) {
            CircularAsyncImage(
                imageUrl = state.imageUrl!!, contentDescription = state.name,
                size = 200.dp
            )
            Text(text = state.name)
        }
    }
}