package com.ss.skillsync.data.repository

import com.ss.skillsync.domain.repository.FriendsRepository
import javax.inject.Inject

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/15/2023.
 */
class FriendRepositoryImpl @Inject constructor(): FriendsRepository {
    override suspend fun getAllFriends(): List<String> {
        return emptyList()
    }

    override suspend fun addUserToFriend(userId: String): Result<Unit> {
        return Result.success(Unit)
    }
}