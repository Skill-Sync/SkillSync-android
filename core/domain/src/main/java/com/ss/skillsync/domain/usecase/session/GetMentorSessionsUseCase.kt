package com.ss.skillsync.domain.usecase.session

import com.ss.skillsync.domain.repository.SessionRepository
import com.ss.skillsync.model.Mentor
import com.ss.skillsync.model.MentorSessions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/11/2023.
 */
class GetMentorSessionsUseCase @Inject constructor(
    private val sessionRepository: SessionRepository,
) {
    suspend operator fun invoke(mentor: Mentor): Result<MentorSessions> {
        return withContext(Dispatchers.IO) {
            sessionRepository.getMentorSessions(mentor)
        }
    }
}
