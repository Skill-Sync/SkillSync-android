package com.ss.skillsync.data.source.remote.session_making.response

import com.ss.skillsync.data.source.remote.model.auth.UserData

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/15/2023.
 */
data class MatchFoundResponse(
    val user1: UserData,
    val user2: UserData,
) {
    fun getMatchUser(currentUserId: String): UserData {
        return if (user1.id == currentUserId) {
            user2
        } else {
            user1
        }
    }
}
