package com.ss.skillsync.settings.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ss.skillsync.commonandroid.components.CircularAsyncImage
import com.ss.skillsync.commonandroid.theme.SkillSyncTheme
import com.ss.skillsync.model.User

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 14/09/2023
 */

@Composable
fun ProfileSection(
    user: User,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CircularAsyncImage(
            imageUrl = user.profilePictureUrl,
            contentDescription = null,
            size = 120.dp,
            modifier = Modifier.padding(bottom = 8.dp),
            placeholderPadding = 30.dp,
        )
        Text(
            text = user.name,
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 18.sp),
            color = Color.White,
            modifier = Modifier.padding(bottom = 3.dp)
        )
        Text(
            text = user.email,
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp),
            color = Color(0xFFC5C5C5)
        )
    }
}

@Preview
@Composable
fun ProfileSectionPreview() {
    SkillSyncTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.TopCenter,
        ) {
            ProfileSection(
                User(
                    name = "Mohannad El-Sayeh",
                    email = "eng.mohannadelsayeh@gmail.com",
                    profilePictureUrl = "https://avatars.githubusercontent.com/u/18093076?v=4",
                )
            )
        }
    }
}