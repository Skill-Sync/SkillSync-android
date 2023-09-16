package com.ss.skillsync.friends

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ss.skillsync.commonandroid.components.ScreenColumn
import com.ss.skillsync.friends.component.FriendsList

@Destination("Friends")
@Composable
fun FriendListScreen(
    viewModel: FriendsViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    FriendLisContent(
        state = state,
    )
}

@Composable
private fun FriendLisContent(
    state: FriendsState,
) {
    ScreenColumn(
        screenLabel = stringResource(R.string.friends),
        isLoading = state.isLoading,
        contentPadding = PaddingValues(
            start = 16.dp,
            end = 16.dp,
            top = 35.dp,
            bottom = 16.dp,
        ),
        arrangement = Arrangement.Top,
    ) {
        FriendsList(friends = state.friends)
    }
}
