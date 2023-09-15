package com.ss.skillsync.editprofile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ss.skillsync.commonandroid.theme.SkillSyncTheme
import com.ss.skillsync.editprofile.UpdatableUser
import com.ss.skillsync.model.User

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 14/09/2023
 */
@Composable
fun EditFormSection(
    user: User,
    onUserUpdated: (UpdatableUser) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        EditTextFieldWithTitle(
            label = "Name",
            value = user.name,
            onValueChange = { onUserUpdated(UpdatableUser(name = it)) })
        EditTextFieldWithTitle(
            label = "Email",
            value = user.email,
            onValueChange = { onUserUpdated(UpdatableUser(email = it)) })

    }
}

@Preview
@Composable
fun EditFormPreview() {
    SkillSyncTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            EditFormSection(User(), {})
        }
    }
}