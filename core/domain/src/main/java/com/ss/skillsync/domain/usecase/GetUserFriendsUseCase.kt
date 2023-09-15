package com.ss.skillsync.domain.usecase

import com.ss.skillsync.domain.repository.FriendsRepository
import com.ss.skillsync.domain.util.StringUtil
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
        return@withContext List(10) {
            User(
                id = "id$it",
                name = "Mohammed Sal",
                email = "email",
                about = "about",
                profilePictureUrl = StringUtil.getRandomImageUrl(),
            )
        }.let {
            Result.success(it)
        }
    }
}