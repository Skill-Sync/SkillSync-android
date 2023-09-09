package com.ss.skillsync.domain.repository

import com.ss.skillsync.model.Skill
import kotlinx.coroutines.flow.Flow

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/8/2023.
 */
interface SkillRepository {

    val userInterestedSkills: Flow<List<Skill>>
    val userStrengths: Flow<List<Skill>>

    suspend fun loadStaticSkills(): List<Skill>
    suspend fun searchSkills(query: String): List<Skill>
    suspend fun addInterestSkill(skill: Skill)
    suspend fun addStrength(skill: Skill)
    suspend fun removeInterestSkill(skill: Skill)
    suspend fun removeStrength(skill: Skill)

    suspend fun updateInterestSkills(skills: List<Skill>): Result<Unit>

    suspend fun updateStrengths(skills: List<Skill>): Result<Unit>
}
