package com.ss.skillsync.domain.usecase.auth

import com.ss.skillsync.domain.payload.SignUpPayload
import com.ss.skillsync.domain.repository.UserRepository
import com.ss.skillsync.domain.util.ValidationUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/8/2023.
 */
class SignupUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {

    suspend operator fun invoke(
        fullName: String,
        email: String,
        password: String,
        confirmPassword: String,
    ): Result<Unit> {
        return try {
            ValidationUtil.validateEmail(email)
            ValidationUtil.validateName(fullName)
            ValidationUtil.validatePasswords(password, confirmPassword)
            val signupPayload = SignUpPayload(
                name = fullName,
                email = email,
                password = password,
            )
            withContext(Dispatchers.IO) {
                userRepository.signUp(signupPayload)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
