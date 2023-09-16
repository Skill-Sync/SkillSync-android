package com.ss.skillsync.domain.usecase.skills

import android.security.keystore.UserNotAuthenticatedException
import com.ss.skillsync.domain.repository.UserRepository
import com.ss.skillsync.model.Skill
import javax.inject.Inject

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/14/2023.
 */
class SearchInterestedSkillsUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {

    companion object {
        private const val MAX_RESULT = 4
    }

    suspend operator fun invoke(query: String): Result<List<Skill>> {
        val userSkills = userRepository
            .getActiveUser()
            .getOrNull()?.interestedSkills ?: return Result.failure(UserNotAuthenticatedException())

        if (userSkills.isEmpty()) {
            return Result.success(emptyList())
        }
        val filteredSkills = userSkills.filter { it.name.contains(query, ignoreCase = true) }
            .take(MAX_RESULT)

        return Result.success(filteredSkills)
    }
}