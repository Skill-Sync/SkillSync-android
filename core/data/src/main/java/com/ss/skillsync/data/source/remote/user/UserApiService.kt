package com.ss.skillsync.data.source.remote.user

import com.ss.skillsync.data.source.remote.interceptor.Authenticated
import com.ss.skillsync.data.source.remote.model.UsersData
import com.ss.skillsync.data.source.remote.model.auth.UserData
import com.ss.skillsync.data.source.remote.model.auth.signin.SignInRequest
import com.ss.skillsync.data.source.remote.model.auth.signup.SignupRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 08/09/2023
 */
interface UserApiService {
    @POST("auth/login")
    suspend fun signIn(@Body signInPayload: SignInRequest): Response<UserData>

    @POST("auth/signup")
    suspend fun signUp(@Body signUpPayload: SignupRequest): Response<UserData>

    @GET("users/Me")
    @Authenticated
    suspend fun getUserData(): Response<UserData>

    @Authenticated
    @GET("users/relevantMentors")
    suspend fun getRelevantMentors(): Response<UsersData>

    @Authenticated
    @PATCH("users/updatePersonalData")
    suspend fun updatePersonalData(@Body userData: UserData): Response<UserData>
}
