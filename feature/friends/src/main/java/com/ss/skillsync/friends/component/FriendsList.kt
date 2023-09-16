package com.ss.skillsync.friends.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ss.skillsync.commonandroid.components.CircularAsyncImage
import com.ss.skillsync.commonandroid.components.HeaderLargeText
import com.ss.skillsync.commonandroid.components.SubHeaderText
import com.ss.skillsync.commonandroid.theme.SkillSyncTheme
import com.ss.skillsync.model.User

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/15/2023.
 */
@Composable
fun FriendsList(
    modifier: Modifier = Modifier,
    friends: List<User>,
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(friends, key = { it.id }) {
            FriendItem(friend = it)
        }
    }
}

@Composable
private fun FriendItem(
    modifier: Modifier = Modifier,
    friend: User,
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        CircularAsyncImage(
            imageUrl = friend.profilePictureUrl,
            contentDescription = friend.name,
            size = 60.dp,
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            HeaderLargeText(
                text = friend.name,
                textAlign = TextAlign.Start,
                fontSize = 20.sp,
            )
            Spacer(modifier = Modifier.height(4.dp))
            SubHeaderText(
                friend.email,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
                ),
            )
        }
    }
}

@Preview
@Composable
private fun FriendItemPreview() {
    SkillSyncTheme {
        FriendItem(
            friend = User(
                id = "1",
                name = "Muhammed Salman",
                email = "Sam@gmail.com",
            ),
        )
    }
}
