package com.ss.skillsync.domain.usecase.session

import com.ss.skillsync.domain.repository.SessionRepository
import javax.inject.Inject

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/11/2023.
 */
class ScheduleSessionUseCase @Inject constructor(
    private val sessionRepository: SessionRepository,
) {

    suspend operator fun invoke(sessionId: String): Result<Unit> {
        return sessionRepository.scheduleSession(sessionId)
    }
}
