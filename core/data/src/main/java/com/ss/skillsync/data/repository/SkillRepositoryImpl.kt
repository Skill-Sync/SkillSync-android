package com.ss.skillsync.data.repository

import com.ss.skillsync.data.mapper.toSkill
import com.ss.skillsync.data.source.remote.profile.SkillRemoteSource
import com.ss.skillsync.domain.repository.SkillRepository
import com.ss.skillsync.model.Skill
import javax.inject.Inject

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/8/2023.
 */
class SkillRepositoryImpl @Inject constructor(
    private val skillRemoteSource: SkillRemoteSource,
) : SkillRepository {

    override suspend fun loadStaticSkills(): List<Skill> {
        return skillRemoteSource.getSkills().map {
            it.toSkill()
        }
    }

    override suspend fun searchSkills(query: String): List<Skill> {
        return skillRemoteSource.searchSkills(query).map {
            it.toSkill()
        }
    }

    override suspend fun setUserSkills(interestedSkills: List<Skill>, strengthSkills: List<Skill>): Result<Unit> {
        val response = skillRemoteSource.setUserSkills(interestedSkills, strengthSkills)
        return if (response.isSuccess) {
            Result.success(Unit)
        } else {
            Result.failure(response.exceptionOrNull()!!)
        }
    }
}
