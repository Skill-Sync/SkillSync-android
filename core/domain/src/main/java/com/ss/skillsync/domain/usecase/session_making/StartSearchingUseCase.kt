package com.ss.skillsync.domain.usecase.session_making

import com.ss.skillsync.domain.repository.SessionMakingRepository
import com.ss.skillsync.domain.repository.UserRepository
import com.ss.skillsync.model.Skill
import javax.inject.Inject

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/14/2023.
 */
class StartSearchingUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val sessionMakingRepository: SessionMakingRepository,
) {

    suspend operator fun invoke(skill: Skill): Result<Unit> {
        val activeUser = userRepository.getActiveUser()
            .onFailure {
                return Result.failure(it)
            }.getOrNull()!!

        sessionMakingRepository.connect(activeUser)
        return Result.success(
            sessionMakingRepository.startSearching(activeUser, skill)
        )
    }
}