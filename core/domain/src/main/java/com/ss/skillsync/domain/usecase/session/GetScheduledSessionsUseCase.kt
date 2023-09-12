package com.ss.skillsync.domain.usecase.session

import com.ss.skillsync.domain.repository.SessionRepository
import com.ss.skillsync.model.Session
import javax.inject.Inject

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/11/2023.
 */
class GetScheduledSessionsUseCase @Inject constructor(
    private val sessionRepository: SessionRepository,
) {
    suspend operator fun invoke(): Result<List<Session>> {
        return sessionRepository.getScheduledSessions()
    }
}
