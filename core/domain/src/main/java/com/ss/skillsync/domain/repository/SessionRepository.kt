package com.ss.skillsync.domain.repository

import com.ss.skillsync.model.Mentor
import com.ss.skillsync.model.MentorSessions
import com.ss.skillsync.model.Session

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/11/2023.
 */
interface SessionRepository {
    suspend fun getScheduledSessions(): Result<List<Session>>

    suspend fun getMentorSessions(mentor: Mentor): Result<MentorSessions>

    suspend fun scheduleSession(sessionId: String): Result<Unit>
}
