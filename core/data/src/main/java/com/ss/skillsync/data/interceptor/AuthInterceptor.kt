package com.ss.skillsync.data.interceptor

import com.ss.skillsync.data.preferences.getUserToken
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.Invocation

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 08/09/2023
 */

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val invocation = chain.request().tag(Invocation::class.java)
            ?: return chain.proceed(chain.request())

        val shouldAttachAuthHeader = invocation
            .method()
            .annotations
            .any { it.annotationClass == Authenticated::class }

        return if (shouldAttachAuthHeader) {
            chain.proceed(
                chain.request()
                    .newBuilder()
                    .addHeader("Authorization", getUserToken())
                    .build()
            )
        } else chain.proceed(chain.request())
    }
}