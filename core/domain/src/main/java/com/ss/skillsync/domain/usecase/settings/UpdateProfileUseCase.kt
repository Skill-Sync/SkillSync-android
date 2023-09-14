package com.ss.skillsync.domain.usecase.settings

import com.ss.skillsync.domain.repository.UserRepository
import com.ss.skillsync.model.User
import javax.inject.Inject

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 14/09/2023
 */
class UpdateProfileUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(user: User): Result<Unit> {
        return userRepository.updatePersonalData(user)
    }
}