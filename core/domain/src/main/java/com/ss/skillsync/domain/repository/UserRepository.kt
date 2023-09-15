package com.ss.skillsync.domain.repository

import com.ss.skillsync.domain.payload.SignInPayload
import com.ss.skillsync.domain.payload.SignUpPayload
import com.ss.skillsync.model.Mentor
import com.ss.skillsync.model.User

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 07/09/2023
 */

interface UserRepository {
    suspend fun getActiveUser(): Result<User>
    suspend fun getActiveUserAsMentor(): Result<Mentor>
    suspend fun signIn(signInPayload: SignInPayload): Result<User>
    suspend fun signUp(signUpPayload: SignUpPayload): Result<Unit>
    suspend fun signOut()
    suspend fun isFirstOpen(): Boolean
    suspend fun getRecommendedMentors(): List<Mentor>
    suspend fun updatePersonalData(user: User): Result<Unit>
}
