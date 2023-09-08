package com.ss.skillsync.data.repository

import com.ss.skillsync.data.mapper.toDomainModel
import com.ss.skillsync.data.model.UserDTO
import com.ss.skillsync.data.preferences.UserPreferences
import com.ss.skillsync.data.source.remote.UserRemoteSource
import com.ss.skillsync.domain.UserRepository
import com.ss.skillsync.domain.payload.SignInPayload
import com.ss.skillsync.domain.payload.SignUpPayload
import com.ss.skillsync.model.User
import javax.inject.Inject

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 07/09/2023
 */

class UserRepositoryImpl @Inject constructor(
    private val userRemoteSource: UserRemoteSource,
    private val preferences: UserPreferences
) : UserRepository {

    private var activeUser: UserDTO? = null

    override suspend fun getActiveUser(): User? {
        return activeUser?.toDomainModel()
    }

    override suspend fun signIn(signInPayload: SignInPayload): Result<User> {
        val response = userRemoteSource.signIn(signInPayload)
        return if (response.isSuccess) {
            activeUser = response.getOrNull()!!
            preferences.saveUserTokens(activeUser!!)
            Result.success(activeUser!!.toDomainModel())
        } else {
            Result.failure(response.exceptionOrNull()!!)
        }
    }

    override suspend fun signUp(signUpPayload: SignUpPayload): Result<Unit> {
        return userRemoteSource.signUp(signUpPayload)
    }

    override suspend fun signOut() {
        activeUser = null
        preferences.clearUserTokens()
    }
}