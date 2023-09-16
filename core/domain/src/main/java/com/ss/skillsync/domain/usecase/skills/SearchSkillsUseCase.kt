package com.ss.skillsync.domain.usecase.skills

import com.ss.skillsync.domain.repository.SkillRepository
import com.ss.skillsync.model.Skill
import javax.inject.Inject

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 09/09/2023
 */

class SearchSkillsUseCase @Inject constructor(
    private val skillRepository: SkillRepository,
) {
    companion object {
        private const val MAX_RESULT = 4
    }
    suspend operator fun invoke(query: String, selectedSkills: Set<Skill>): Result<Set<Skill>> {
        return try {
            val skills = (skillRepository.searchSkills(query).toSet() - selectedSkills)
                .take(MAX_RESULT).toSet()

            Result.success(skills)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
