package com.ss.skillsync.domain.repository

import com.ss.skillsync.model.Mentor

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 10/09/2023
 */
interface MentorRepository {

    var selectedMentor: Mentor?
    suspend fun acceptSession(sessionId: String): Result<Unit>

    suspend fun rejectSession(sessionId: String): Result<Unit>

    suspend fun setWorkingHours(from: String, to: String): Result<Unit>
}
