package com.ss.skillsync.domain.fake

import com.ss.skillsync.domain.repository.SkillRepository
import com.ss.skillsync.model.Skill
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/8/2023.
 */
class FakeSkillRepository : SkillRepository {

    private val staticSkills = listOf(
        Skill(id = "1", name = "Programming"),
        Skill(id = "2", name = "Design"),
        Skill(id = "3", name = "Communication"),
        Skill(id = "4", name = "Leadership"),
    )
    private val userInterestedSkillsFlow = MutableStateFlow<List<Skill>>(emptyList())
    private val userStrengthsFlow = MutableStateFlow<List<Skill>>(emptyList())

    override suspend fun loadStaticSkills(): List<Skill> {
        return staticSkills.toList()
    }

    override suspend fun searchSkills(query: String): List<Skill> {
        return staticSkills.filter { it.name.contains(query, ignoreCase = true) }
    }

    override suspend fun setUserSkills(
        interestedSkills: List<Skill>,
        strengthSkills: List<Skill>,
    ): Result<Unit> {
        userInterestedSkillsFlow.value = interestedSkills
        userStrengthsFlow.value = strengthSkills
        return Result.success(Unit)
    }
}
