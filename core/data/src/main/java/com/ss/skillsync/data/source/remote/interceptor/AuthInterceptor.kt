package com.ss.skillsync.data.source.remote.interceptor

import com.ss.skillsync.data.preferences.UserPreferences
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.Invocation
import javax.inject.Inject

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 08/09/2023
 */

class AuthInterceptor @Inject constructor(
    private val userPreferences: UserPreferences,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val invocation = chain.request().tag(Invocation::class.java)
            ?: return chain.proceed(chain.request())

        val shouldAttachAuthHeader = invocation
            .method()
            .annotations
            .any { it.annotationClass == Authenticated::class }

        return runBlocking {
            chain.handleRequest(shouldAttachAuthHeader)
        }
    }

    private suspend fun Interceptor.Chain.handleRequest(shouldAttachAuthHeader: Boolean): Response {
        return if (shouldAttachAuthHeader) {
            val response = proceed(
                request()
                    .newBuilder()
                    .addHeader("Authorization", userPreferences.getUserToken())
                    .build()
            )
            handleResponse(response)
        } else handleResponse(proceed(request()))
    }

    private suspend fun handleResponse(response: Response): Response {
        val refreshTokenHeader = "RefreshToken"
        val accessTokenHeader = "AccessToken"
        val refreshToken = response.header(refreshTokenHeader)
        val accessToken = response.header(accessTokenHeader)
        if (refreshToken == null || accessToken == null) {
            return response
        }

        userPreferences.saveUserTokens(accessToken, refreshToken)
        return response
    }
}