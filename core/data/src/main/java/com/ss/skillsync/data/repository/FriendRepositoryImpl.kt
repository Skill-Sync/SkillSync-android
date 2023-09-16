package com.ss.skillsync.data.repository

import com.ss.skillsync.data.mapper.toDomain
import com.ss.skillsync.data.source.remote.friends.FriendsRemoteSource
import com.ss.skillsync.domain.repository.FriendsRepository
import com.ss.skillsync.model.User
import javax.inject.Inject

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/15/2023.
 */
class FriendRepositoryImpl @Inject constructor(
    private val friendsRemoteSource: FriendsRemoteSource
): FriendsRepository {
    override suspend fun getAllFriends(): Result<List<User>> {
        val response = friendsRemoteSource.getAllFriends()
        return if (response.isSuccess) {
            val users = response.getOrNull()!!.map {
                it.toDomain()
            }
            Result.success(users)
        } else {
            Result.failure(response.exceptionOrNull()!!)
        }
    }

    override suspend fun addUserToFriend(userId: String): Result<Unit> {
        return Result.success(Unit)
    }
}