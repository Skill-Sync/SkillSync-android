package com.ss.skillsync.domain.usecase.session_making

import com.ss.skillsync.domain.repository.FriendsRepository
import javax.inject.Inject

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/14/2023.
 */
class AddUserToFriendsUseCase @Inject constructor(
    private val friendsRepository: FriendsRepository
) {
    suspend operator fun invoke(userId: String): Result<Unit> {
        return friendsRepository.addUserToFriend(userId)
    }
}