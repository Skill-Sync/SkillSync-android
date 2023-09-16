package com.ss.skillsync.data.source.remote.user

import com.ss.skillsync.data.source.remote.model.auth.UserData
import com.ss.skillsync.data.source.remote.model.auth.signin.SignInRequest
import com.ss.skillsync.data.source.remote.model.auth.signup.SignupRequest
import com.ss.skillsync.domain.payload.SignInPayload
import com.ss.skillsync.domain.payload.SignUpPayload
import com.ss.skillsync.model.exception.EmailNotActivatedException
import com.ss.skillsync.model.exception.UnknownErrorException
import javax.inject.Inject

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/8/2023.
 */
class UserRemoteSource @Inject constructor(
    private val apiService: UserApiService,
) {

    companion object {
        private const val TAG = "UserRemoteSource"
    }

    suspend fun signUp(
        payload: SignUpPayload,
    ): Result<Unit> {
        return try {
            val request = SignupRequest(
                name = payload.name,
                email = payload.email,
                pass = payload.password,
                passConfirm = payload.password,
            )
            val response = apiService.signUp(request)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Throwable(response.errorBody()?.string()))
            }
        } catch (e: Exception) {
            com.timers.stopwatch.core.log.error(TAG, e)
            Result.failure(e)
        }
    }

    suspend fun signIn(
        payload: SignInPayload,
    ): Result<UserData> {
        return try {
            val request = SignInRequest(
                email = payload.email,
                pass = payload.password,
                type = payload.type,
            )
            val response = apiService.signIn(request)
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                val errorCode = response.code()
                Result.failure(handleSignInError(errorCode))
            }
        } catch (e: Exception) {
            com.timers.stopwatch.core.log.error(TAG, e)
            Result.failure(e)
        }
    }

    suspend fun getUserData(): UserData? {
        return try {
            val response = apiService.getUserData()
            response.body().also {
                println(it)
            }
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getRelevantMentors(): List<UserData>? {
        return try {
            val response = apiService.getRelevantMentors()
            response.body()!!.users.also {
                println(it)
            }
        } catch (e: Exception) {
            com.timers.stopwatch.core.log.error(TAG, e)
            null
        }
    }

    suspend fun updateUserData(userData: UserData): Result<Unit> {
        return try {
            val response = apiService.updatePersonalData(userData)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Throwable(response.errorBody()?.string()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun handleSignInError(code: Int): Exception {
        val inactivatedEmailCode = 401
        return if (code == inactivatedEmailCode) {
            EmailNotActivatedException()
        } else {
            UnknownErrorException()
        }
    }
}
