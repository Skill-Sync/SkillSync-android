package com.ss.skillsync.data.source.remote.mentors

import javax.inject.Inject

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 10/09/2023
 */
class MentorRemoteSource @Inject constructor(
    private val mentorApiService: MentorApiService,
) {

    suspend fun acceptSession(sessionId: String): Result<Unit> {
        return kotlin.runCatching {
            mentorApiService.answerSessionRequest(sessionId, "accepted")
        }
    }

    suspend fun rejectSession(sessionId: String): Result<Unit> {
        return kotlin.runCatching {
            mentorApiService.answerSessionRequest(sessionId, "rejected")
        }
    }

    suspend fun setWorkingHours(from: String, to: String): Result<Unit> {
        return kotlin.runCatching {
            val workingHours = "$from-$to"
            mentorApiService.setWorkingHours(workingHours)
        }
    }
}
