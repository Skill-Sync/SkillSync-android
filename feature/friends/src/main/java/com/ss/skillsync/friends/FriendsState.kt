package com.ss.skillsync.friends

import com.ss.skillsync.model.User

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/15/2023.
 */
data class FriendsState(
    val isLoading: Boolean = false,
    val friends: List<User> = emptyList(),
    val failedRetrievingFriends: Boolean = false,
)
