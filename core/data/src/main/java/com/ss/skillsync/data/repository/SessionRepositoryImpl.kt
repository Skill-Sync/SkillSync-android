package com.ss.skillsync.data.repository

import com.ss.skillsync.data.mapper.toDomain
import com.ss.skillsync.data.source.remote.session.SessionRemoteSource
import com.ss.skillsync.domain.repository.SessionRepository
import com.ss.skillsync.model.Mentor
import com.ss.skillsync.model.MentorSessions
import com.ss.skillsync.model.Session
import javax.inject.Inject

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/11/2023.
 */
class SessionRepositoryImpl @Inject constructor(
    private val sessionRemoteSource: SessionRemoteSource,
) : SessionRepository {
    override suspend fun getScheduledSessions(): Result<List<Session>> {
        val response = sessionRemoteSource.getUserScheduledSessions()
        if (response.isFailure) {
            return Result.failure(response.exceptionOrNull()!!)
        }
        val sessions = response.getOrNull()!!.sessions.map {
            it.toDomain()
        }
        return Result.success(sessions)
    }

    override suspend fun getMentorSessions(mentor: Mentor): Result<MentorSessions> {
        val mentorId = mentor.id
        val sessions = sessionRemoteSource.getMentorSessions(mentorId)
        if (sessions.isFailure) {
            return Result.failure(sessions.exceptionOrNull()!!)
        }
        val mentorSessions = MentorSessions(
            mentor = mentor,
            sessions = sessions.getOrNull()!!.sessions.map {
                it.toDomain()
            },
        )
        return Result.success(mentorSessions)
    }

    override suspend fun scheduleSession(sessionId: String): Result<Unit> {
        val response = sessionRemoteSource.scheduleSession(sessionId)
        if (response.isFailure) {
            return Result.failure(response.exceptionOrNull()!!)
        }
        return Result.success(Unit)
    }
}
