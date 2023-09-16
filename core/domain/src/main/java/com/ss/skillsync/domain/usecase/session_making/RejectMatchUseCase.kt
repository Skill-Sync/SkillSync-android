package com.ss.skillsync.domain.usecase.session_making

import com.ss.skillsync.domain.repository.SessionMakingRepository
import javax.inject.Inject

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/14/2023.
 */
class RejectMatchUseCase @Inject constructor(
    private val sessionMakingRepository: SessionMakingRepository
) {

    suspend operator fun invoke(): Result<Unit> {
        return Result.success(sessionMakingRepository.rejectMatch())
    }
}