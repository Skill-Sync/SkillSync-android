package com.ss.skillsync.domain.usecase.auth

import com.ss.skillsync.domain.repository.UserRepository
import javax.inject.Inject

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/11/2023.
 */
class GetActiveUserUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {

    suspend operator fun invoke() = userRepository.getActiveUser()
}
