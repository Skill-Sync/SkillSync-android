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
    suspend operator fun invoke(query: String, selectedSkills: Set<Skill>): Result<Set<Skill>> {
        // TODO: Update the testing code to get real data
        return Result.success(
            setOf(
                Skill(id = 0, name = "Skill 1"),
                Skill(id = 1, name = "Skill 2 gdh gduihds"),
                Skill(id = 2, name = "Skill 3")
            )
        )
        /*try {
                val skills = skillRepository.searchSkills(query).toSet() - selectedSkills
                Result.success(skills)
            } catch (e: Exception) {
                Result.failure(e)
            }*/
    }
}