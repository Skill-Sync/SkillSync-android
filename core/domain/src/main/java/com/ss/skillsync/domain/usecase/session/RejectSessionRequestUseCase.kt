package com.ss.skillsync.domain.usecase.session

import com.ss.skillsync.domain.repository.MentorRepository
import javax.inject.Inject

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/11/2023.
 */
class RejectSessionRequestUseCase @Inject constructor(
    private val mentorRepository: MentorRepository,
) {
    suspend operator fun invoke(sessionId: String): Result<Unit> {
        return mentorRepository.rejectSession(sessionId)
    }
}
