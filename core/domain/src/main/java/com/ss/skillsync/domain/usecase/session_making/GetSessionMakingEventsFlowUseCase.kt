package com.ss.skillsync.domain.usecase.session_making

import com.ss.skillsync.domain.repository.SessionMakingRepository
import com.ss.skillsync.model.SessionMakingEvent
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/14/2023.
 */
class GetSessionMakingEventsFlowUseCase @Inject constructor(
    private val sessionMakingRepository: SessionMakingRepository,
) {
    operator fun invoke(): Flow<SessionMakingEvent> {
        return sessionMakingRepository.eventsFlow
    }
}