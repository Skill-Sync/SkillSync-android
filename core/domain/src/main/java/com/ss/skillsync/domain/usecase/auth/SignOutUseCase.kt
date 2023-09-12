package com.ss.skillsync.domain.usecase.auth

import com.ss.skillsync.domain.repository.UserRepository
import javax.inject.Inject

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 12/09/2023
 */
class SignOutUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke() =
        userRepository.signOut()
}