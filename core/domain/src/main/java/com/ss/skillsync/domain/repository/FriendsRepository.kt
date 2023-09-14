package com.ss.skillsync.domain.repository

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/14/2023.
 */
interface FriendsRepository {

    suspend fun getAllFriends(): List<String>

    suspend fun addUserToFriend(userId: String): Result<Unit>
}