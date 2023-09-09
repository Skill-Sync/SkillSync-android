package com.ss.skillsync.data.source.remote.user

import com.ss.skillsync.data.model.UserDTO
import com.ss.skillsync.data.source.remote.interceptor.Authenticated
import com.ss.skillsync.domain.payload.SignInPayload
import com.ss.skillsync.domain.payload.SignUpPayload
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 08/09/2023
 */
interface UserApiService {
    @POST("signIn")
    suspend fun signIn(@Body signInPayload: SignInPayload): Response<UserDTO>

    @POST("signUp")
    suspend fun signUp(@Body signUpPayload: SignUpPayload): Response<String>

    @GET("getUserData")
    @Authenticated
    suspend fun getUserData(): Response<UserDTO>
}