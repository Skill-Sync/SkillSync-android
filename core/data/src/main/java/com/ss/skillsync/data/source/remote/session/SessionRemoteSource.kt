package com.ss.skillsync.data.source.remote.session

import com.ss.skillsync.data.source.remote.model.session.SessionsResponse
import javax.inject.Inject

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/11/2023.
 */
class SessionRemoteSource @Inject constructor(
    private val sessionApiService: SessionApiService,
) {

    companion object {
        private const val TAG = "SessionRemoteSource"
    }

    suspend fun getUserScheduledSessions(): Result<SessionsResponse> {
        return kotlin.runCatching {
            val response = sessionApiService.getScheduledSessions()
            if (response.isSuccessful) {
                response.body()!!
            } else {
                throw Throwable(response.errorBody()?.string())
            }
        }.onFailure {
            com.timers.stopwatch.core.log.error(TAG, it)
        }
    }

    suspend fun getMentorSessions(mentorId: String): Result<SessionsResponse> {
        return kotlin.runCatching {
            val response = sessionApiService.getMentorSessions(mentorId)
            if (response.isSuccessful) {
                response.body()!!
            } else {
                throw Throwable(response.errorBody()?.string())
            }
        }.onFailure {
            com.timers.stopwatch.core.log.error(TAG, it)
        }
    }

    suspend fun scheduleSession(sessionId: String): Result<Unit> {
        return kotlin.runCatching {
            sessionApiService.scheduleSession(sessionId)
        }.onFailure {
            com.timers.stopwatch.core.log.error(TAG, it)
        }
    }
}
