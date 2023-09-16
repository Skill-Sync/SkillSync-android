package com.ss.skillsync.domain.fake

import com.ss.skillsync.domain.repository.SessionRepository
import com.ss.skillsync.model.Mentor
import com.ss.skillsync.model.MentorSessions
import com.ss.skillsync.model.Session

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/16/2023.
 */
class FakeSessionRepository : SessionRepository {
    private val mentorSessionsMap = mutableMapOf<Mentor, MentorSessions>()
    private val scheduledSessions = mutableListOf<Session>()

    // Add predefined data for testing
    fun addMentorSessions(mentor: Mentor, sessions: List<Session>) {
        mentorSessionsMap[mentor] = MentorSessions(mentor, sessions)
    }

    fun addScheduledSession(session: Session) {
        scheduledSessions.add(session)
    }

    override suspend fun getScheduledSessions(): Result<List<Session>> {
        return Result.success(scheduledSessions)
    }

    override suspend fun getMentorSessions(mentor: Mentor): Result<MentorSessions> {
        val mentorSessions = mentorSessionsMap[mentor]
        return if (mentorSessions != null) {
            Result.success(mentorSessions)
        } else {
            Result.failure(Exception("Mentor sessions not found"))
        }
    }

    override suspend fun scheduleSession(sessionId: String): Result<Unit> {
        val session = scheduledSessions.find { it.sessionId == sessionId }
        return if (session != null) {
            // Remove the scheduled session to simulate scheduling
            scheduledSessions.remove(session)
            Result.success(Unit)
        } else {
            Result.failure(Exception("Session not found"))
        }
    }
}