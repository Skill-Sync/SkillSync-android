package com.ss.skillsync.data.source.remote.mentors

import com.ss.skillsync.data.source.remote.interceptor.Authenticated
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 10/09/2023
 */
interface MentorApiService {
    @Authenticated
    @POST("meetings/{sessionId}")
    suspend fun answerSessionRequest(
        @Path(value = "sessionId") sessionId: String,
        @Body status: String,
    )

    @Authenticated
    @POST("mentors/set-working-hours")
    suspend fun setWorkingHours(
        @Body workingHours: String,
    )
}
