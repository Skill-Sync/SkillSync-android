package com.ss.skillsync.domain.usecase

import com.ss.skillsync.domain.repository.UserRepository
import com.ss.skillsync.model.NavigationParams
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/8/2023.
 */
class GetNavigationParamsUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(): Result<NavigationParams> {
        return withContext(Dispatchers.IO) {
            try {
                val isFirstOpen = userRepository.isFirstOpen()
                val isUserActive = userRepository.getActiveUser().isSuccess
                Result.success(NavigationParams(isFirstOpen, isUserActive))
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}
