package com.ss.skillsync.data.source.remote.friends

import javax.inject.Inject

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/15/2023.
 */
class FriendsRemoteSource @Inject constructor(
    private val friendsApiService: FriendsApiService,
) {

    suspend fun getAllFriends() = kotlin.runCatching {
        val response = friendsApiService.getAllFriends()
        response.body()?.users ?: emptyList()
    }
}