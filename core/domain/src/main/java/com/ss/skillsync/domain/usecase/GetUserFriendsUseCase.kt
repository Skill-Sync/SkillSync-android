package com.ss.skillsync.domain.usecase

import com.ss.skillsync.domain.repository.FriendsRepository
import com.ss.skillsync.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/15/2023.
 */
class GetUserFriendsUseCase @Inject constructor(
    private val friendsRepository: FriendsRepository
) {

    suspend operator fun invoke(): Result<List<User>> = withContext(Dispatchers.IO) {
        val result = friendsRepository.getAllFriends()
        if (result.isSuccess) {
            val friends = result.getOrNull()!!
            Result.success(friends)
        } else {
            Result.failure(result.exceptionOrNull()!!)
        }
    }
}