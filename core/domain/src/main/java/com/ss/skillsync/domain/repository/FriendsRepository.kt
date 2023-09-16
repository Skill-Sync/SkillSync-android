package com.ss.skillsync.domain.repository

import com.ss.skillsync.model.User

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/14/2023.
 */
interface FriendsRepository {

    suspend fun getAllFriends(): Result<List<User>>

    suspend fun addUserToFriend(userId: String): Result<Unit>
}