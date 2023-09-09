package com.ss.skillsync.domain.usecase.skills

import com.ss.skillsync.domain.repository.SkillRepository
import com.ss.skillsync.model.Skill
import javax.inject.Inject

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 09/09/2023
 */
class SaveSkillsUseCase @Inject constructor(
    private val skillRepository: SkillRepository,
) {
    suspend operator fun invoke(skills: Set<Skill>): Result<Unit> {
        return try {
            skillRepository.updateInterestSkills(skills.toList())
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}