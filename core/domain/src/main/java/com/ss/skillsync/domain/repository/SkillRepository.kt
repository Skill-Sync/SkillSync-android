package com.ss.skillsync.domain.repository

import com.ss.skillsync.model.Skill

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/8/2023.
 */
interface SkillRepository {

    suspend fun loadStaticSkills(): List<Skill>
    suspend fun searchSkills(query: String): List<Skill>

    suspend fun setUserSkills(
        interestedSkills: List<Skill>,
        strengthSkills: List<Skill>,
    ): Result<Unit>
}
