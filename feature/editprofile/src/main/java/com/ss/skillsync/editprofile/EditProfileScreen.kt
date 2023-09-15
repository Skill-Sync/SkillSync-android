package com.ss.skillsync.editprofile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ss.skillsync.commonandroid.components.BrandButton
import com.ss.skillsync.commonandroid.components.ScreenColumn
import com.ss.skillsync.commonandroid.theme.SkillSyncTheme
import com.ss.skillsync.editprofile.components.EditFormSection
import com.ss.skillsync.editprofile.components.EditPictureSection
import com.ss.skillsync.editprofile.components.EditSkillsSection

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 14/09/2023
 */

@Destination
@Composable
fun EditProfileScreen(
    navigator: EditProfileNavigator,
    snackbarHostState: SnackbarHostState,
    viewModel: EditProfileViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(key1 = state.backPressed) {
        if (state.backPressed)
            navigator.popBackStack()
        viewModel.resetEvents()
    }

    LaunchedEffect(key1 = state.error) {
        if (state.error != null) {
            snackbarHostState.showSnackbar(state.error!!.message.toString())
        }
        viewModel.resetEvents()
    }

    LaunchedEffect(key1 = state.navigationDestination) {
        when (state.navigationDestination) {
            EditProfileDestination.Onboarding -> navigator.navigateToOnboarding()
            null -> return@LaunchedEffect
        }
        viewModel.resetEvents()
    }

    EditProfileContent(
        state,
        viewModel::onEvent
    )
}

@Composable
fun EditProfileContent(
    state: EditProfileState,
    onEvent: (EditProfileEvent) -> Unit,
) {
    ScreenColumn(
        isBackDisplayed = true,
        onBackClicked = { onEvent(EditProfileEvent.BackPressed) },
        screenLabel = "Edit Profile",
        contentPadding = PaddingValues(horizontal = 32.dp),
    ) {
        EditPictureSection(
            userImageUrl = state.updatedUser.profilePictureUrl,
            modifier = Modifier.size(130.dp),
            addPhotoSize = 34.dp,
        )
        EditFormSection(
            user = state.updatedUser,
            onUserUpdated = { onEvent(EditProfileEvent.UpdateUser(it)) },
        )
        EditSkillsSection(
            onEditSkills = { onEvent(EditProfileEvent.EditSkills) },
            modifier = Modifier.fillMaxWidth()
        )
        BrandButton(
            text = "Update",
            onClick = { onEvent(EditProfileEvent.SaveProfile) },
            modifier = Modifier.fillMaxWidth(0.8f),
            isUppercase = false,
            background = Brush.horizontalGradient(
                colors = listOf(
                    Color(0xFF672FB6),
                    Color(0xFF4A67E5),
                    Color(0xFF3A86FF),
                )
            ),
            textColor = Color.White,
        )
    }
}

@Preview
@Composable
fun EditProfileScreenPreview() {
    SkillSyncTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            EditProfileContent(EditProfileState(), {})
        }
    }
}