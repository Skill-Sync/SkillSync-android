package com.ss.skillsync.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ss.skillsync.commonandroid.components.WelcomeCard
import com.ss.skillsync.commonandroid.theme.SkillSyncTheme
import com.ss.skillsync.model.User

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 11/09/2023
 */

@Composable
fun TopHomeBar(
    user: User,
    onProfileClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    ) {
        WelcomeCard(
            imageUrl = user.profilePictureUrl,
            name = user.name,
            onClick = onProfileClicked
        )
        Icon(
            imageVector = Icons.TwoTone.Settings,
            contentDescription = "Settings",
            tint = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.size(26.dp)
        )
    }
}

@Preview
@Composable
fun TobHomeBarPreview() {
    SkillSyncTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            TopHomeBar(
                user = User(
                    name = "Mohannad El-Sayeh",
                    profilePictureUrl = "https://avatars.githubusercontent.com/u/10069642?v=4",
                ),
                onProfileClicked = {}
            )
        }
    }
}