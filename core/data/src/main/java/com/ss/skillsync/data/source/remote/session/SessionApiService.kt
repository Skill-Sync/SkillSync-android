package com.ss.skillsync.data.source.remote.session

import com.ss.skillsync.data.source.remote.interceptor.Authenticated
import com.ss.skillsync.data.source.remote.model.session.SessionsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/11/2023.
 */
interface SessionApiService {
    @Authenticated
    @GET("meetings")
    suspend fun getScheduledSessions(): Response<SessionsResponse>

    @Authenticated
    @GET("mentors/meetings/{mentorId}")
    suspend fun getMentorSessions(
        @Path(value = "mentorId") mentorId: String,
    ): Response<SessionsResponse>

    @Authenticated
    @PATCH("meetings/{sessionId}")
    suspend fun scheduleSession(
        @Path(value = "sessionId") sessionId: String,
    )
}
