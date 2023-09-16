package com.ss.skillsync.data.source.remote.friends

import javax.inject.Inject

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/15/2023.
 */
class FriendsRemoteSource @Inject constructor(
    private val friendsApiService: FriendsApiService,
) {

    companion object {
        private const val TAG = "FriendsRemoteSource"
    }

    suspend fun getAllFriends() = kotlin.runCatching {
        val response = friendsApiService.getAllFriends()
        response.body()?.friends ?: emptyList()
    }.onFailure {
        com.timers.stopwatch.core.log.error(TAG, it)
    }
}