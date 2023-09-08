package com.ss.skillsync.domain.usecase

import com.ss.skillsync.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/8/2023.
 */
class IsUserActiveUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(): Result<Unit> {
        return withContext(Dispatchers.IO) {
            val activeUser = userRepository.getActiveUser()
            if (activeUser.isSuccess) {
                Result.success(Unit)
            } else {
                Result.failure(activeUser.exceptionOrNull()!!)
            }
        }
    }
}
