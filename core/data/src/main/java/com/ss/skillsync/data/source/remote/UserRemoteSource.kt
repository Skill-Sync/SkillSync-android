package com.ss.skillsync.data.source.remote

import com.ss.skillsync.data.model.UserDTO
import com.ss.skillsync.domain.payload.SignInPayload
import com.ss.skillsync.domain.payload.SignUpPayload
import javax.inject.Inject

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/8/2023.
 */
class UserRemoteSource @Inject constructor(
    private val apiService: UserApiService
) {

    suspend fun signUp(
        payload: SignUpPayload,
    ): Result<Unit> {
        return try {
            val response = apiService.signUp(payload)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Throwable(response.errorBody()?.string()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun signIn(
        payload: SignInPayload,
    ): Result<UserDTO> {
        return try {
            val response = apiService.signIn(payload)
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Throwable(response.errorBody()?.string()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}