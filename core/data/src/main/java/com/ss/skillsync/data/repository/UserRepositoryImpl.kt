package com.ss.skillsync.data.repository

import com.ss.skillsync.data.mapper.toDomain
import com.ss.skillsync.data.mapper.toMentor
import com.ss.skillsync.data.mapper.toUserData
import com.ss.skillsync.data.preferences.UserPreferences
import com.ss.skillsync.data.source.remote.model.auth.UserData
import com.ss.skillsync.data.source.remote.user.UserRemoteSource
import com.ss.skillsync.domain.payload.SignInPayload
import com.ss.skillsync.domain.payload.SignUpPayload
import com.ss.skillsync.domain.repository.UserRepository
import com.ss.skillsync.model.Mentor
import com.ss.skillsync.model.User
import javax.inject.Inject

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 07/09/2023
 */

class UserRepositoryImpl @Inject constructor(
    private val userRemoteSource: UserRemoteSource,
    private val preferences: UserPreferences,
) : UserRepository {

    private var activeUser: UserData? = null

    override suspend fun getActiveUser(): Result<User> {
        if (preferences.areTokensAvailable().not()) {
            return Result.failure(Throwable("SignupResponseUser not found"))
        }

        if (activeUser == null) {
            activeUser = userRemoteSource.getUserData()
        }
        return if (activeUser != null) {
            Result.success(activeUser!!.toDomain())
        } else {
            signOut()
            Result.failure(Throwable("SignupResponseUser not found"))
        }
    }

    override suspend fun getActiveUserAsMentor(): Result<Mentor> {
        if (preferences.areTokensAvailable().not()) {
            return Result.failure(Throwable("SignupResponseUser not found"))
        }

        if (activeUser == null) {
            activeUser = userRemoteSource.getUserData()
        }
        return if (activeUser != null) {
            Result.success(activeUser!!.toMentor())
        } else {
            signOut()
            Result.failure(Throwable("SignupResponseUser not found"))
        }
    }

    override suspend fun signIn(signInPayload: SignInPayload): Result<User> {
        val response = userRemoteSource.signIn(signInPayload)
        return if (response.isSuccess) {
            activeUser = response.getOrNull()!!
            preferences.saveUserTokens(activeUser!!)
            Result.success(activeUser!!.toDomain())
        } else {
            signOut()
            Result.failure(response.exceptionOrNull()!!)
        }
    }

    override suspend fun signUp(signUpPayload: SignUpPayload): Result<Unit> {
        return userRemoteSource.signUp(signUpPayload).onFailure {
            signOut()
        }
    }

    override suspend fun signOut() {
        activeUser = null
        preferences.clearUserTokens()
    }

    override suspend fun isFirstOpen(): Boolean {
        return preferences.isFirstOpen()
    }

    override suspend fun getRecommendedMentors(): List<Mentor> {
        return userRemoteSource.getRelevantMentors()?.map { it.toMentor() } ?: emptyList()
    }

    override suspend fun updatePersonalData(user: User): Result<Unit> {
        val response = userRemoteSource.updateUserData(user.toUserData()).onSuccess {
            activeUser = user.toUserData()
        }
        return response
    }
}
