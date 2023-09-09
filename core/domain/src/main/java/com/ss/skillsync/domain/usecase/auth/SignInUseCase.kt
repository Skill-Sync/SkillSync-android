package com.ss.skillsync.domain.usecase.auth

import com.ss.skillsync.domain.BuildConfig
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
    ): Result<User> {
        if (BuildConfig.DEBUG && email == "test" && password == "test") {
            return Result.success(User(
                name = listOf("Mohannad El-Sayeh", "Muhammed Salman").random(),
                email = listOf("eng.mohannadelsayeh@gmail.com", "mahmadslman@gmail.com").random(),
                profilePictureUrl = "https://avatars.githubusercontent.com/u/18093076?v=4",
                onboardingCompleted = false,
            ))
        }

        return try {
            ValidationUtil.validateEmail(email)
            ValidationUtil.validatePassword(password)
            val signInPayload = SignInPayload(
                email = email,
                password = password,
            )
            withContext(Dispatchers.IO) {
                userRepository.signIn(signInPayload)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
