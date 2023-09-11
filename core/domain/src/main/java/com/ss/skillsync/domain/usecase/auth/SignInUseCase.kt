package com.ss.skillsync.domain.usecase.auth

import com.ss.skillsync.domain.BuildConfig
import com.ss.skillsync.domain.payload.SignInPayload
import com.ss.skillsync.domain.repository.UserRepository
import com.ss.skillsync.domain.util.ValidationUtil
import com.ss.skillsync.model.User
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
        type: String,
    ): Result<User> {
        if (BuildConfig.DEBUG && email.isBlank() && password.isBlank()) {
            return try {
                userRepository.signIn(
                    SignInPayload(
                        email = BuildConfig.userEmail,
                        password = BuildConfig.userPass,
                        type = type,
                    ),
                )
            } catch (e: Exception) {
                Result.failure(e)
            }
        }

        return try {
            ValidationUtil.validateEmail(email)
            ValidationUtil.validatePassword(password)
            val signInPayload = SignInPayload(
                email = email,
                password = password,
                type = type,
            )
            userRepository.signIn(signInPayload)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
