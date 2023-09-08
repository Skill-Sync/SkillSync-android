package com.ss.skillsync.data

import com.ss.skillsync.data.mapper.toDomainModel
import com.ss.skillsync.data.preferences.clearUserTokens
import com.ss.skillsync.data.preferences.getUserToken
import com.ss.skillsync.data.preferences.saveUserTokens
import com.ss.skillsync.data.retrofit.UserApiService
import com.ss.skillsync.domain.UserRepository
import com.ss.skillsync.domain.payload.SignInPayload
import com.ss.skillsync.domain.payload.SignUpPayload
import com.ss.skillsync.model.User
import javax.inject.Inject
import javax.net.ssl.HttpsURLConnection

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 07/09/2023
 */

class UserRepositoryImpl @Inject constructor(
    private val userApiService: UserApiService,
) : UserRepository {

    private var activeUser: UserDTO? = null
        set(value) {
            value?.let { saveUserTokens(it) }
            field = value
        }

    override suspend fun getActiveUser(): User? {
        userApiService.getUserDataWithToken(getUserToken())
        return activeUser?.toDomainModel()
    }

    override suspend fun signIn(signInPayload: SignInPayload): Result<User> {
        val response = userApiService.signIn(signInPayload)
        return if (response.isSuccessful) {
            activeUser = response.body()
            Result.success(activeUser!!.toDomainModel())
        } else {
            Result.failure(Throwable(response.errorBody()?.string()))
        }
    }

    override suspend fun signUp(signUpPayload: SignUpPayload): Result<String> {
        return if (userApiService.signUp(signUpPayload).code() == HttpsURLConnection.HTTP_CREATED) {
            Result.success("Success")
        } else {
            Result.failure(Throwable("Error signing up"))
        }
    }

    override fun signOut() {
        activeUser = null
        clearUserTokens()
    }
}