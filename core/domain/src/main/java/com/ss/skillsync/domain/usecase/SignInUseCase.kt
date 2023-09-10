package com.ss.skillsync.domain.usecase

import com.ss.skillsync.domain.payload.SignInPayload
import com.ss.skillsync.domain.repository.UserRepository
import com.ss.skillsync.domain.util.ValidationUtil
import com.ss.skillsync.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/8/2023.
 */
class SignInUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(
        email: String,
        password: String,
        type: String
    ): Result<User> {
        return try {
            ValidationUtil.validateEmail(email)
            ValidationUtil.validatePassword(password)
            val signupPayload = SignInPayload(
                email = email,
                password = password,
                type = type
            )
            withContext(Dispatchers.IO) {
                userRepository.signIn(signupPayload)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
