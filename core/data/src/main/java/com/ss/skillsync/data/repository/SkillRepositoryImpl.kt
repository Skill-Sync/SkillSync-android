package com.ss.skillsync.data.repository

import com.ss.skillsync.data.mapper.toDomainModel
import com.ss.skillsync.data.source.remote.onboarding.SkillRemoteSource
import com.ss.skillsync.domain.repository.SkillRepository
import com.ss.skillsync.model.Skill
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/8/2023.
 */
class SkillRepositoryImpl @Inject constructor(
    private val skillRemoteSource: SkillRemoteSource
): SkillRepository {

    private val userInterestedSkillsFlow = MutableStateFlow<List<Skill>>(emptyList())
    private val userStrengthsFlow = MutableStateFlow<List<Skill>>(emptyList())
    override val userInterestedSkills: Flow<List<Skill>>
        get() = userInterestedSkillsFlow
    override val userStrengths: Flow<List<Skill>>
        get() = userStrengthsFlow

    override suspend fun loadStaticSkills(): List<Skill> {
        return skillRemoteSource.getSkills().map {
            it.toDomainModel()
        }
    }

    override suspend fun searchSkills(query: String): List<Skill> {
        return skillRemoteSource.searchSkills(query).map {
            it.toDomainModel()
        }
    }

    override suspend fun addInterestSkill(skill: Skill) {
        val currentSkills = userInterestedSkillsFlow.value.toMutableList()
        currentSkills.add(skill)
        userInterestedSkillsFlow.update {
            currentSkills
        }
    }

    override suspend fun addStrength(skill: Skill) {
        val currentSkills = userStrengthsFlow.value.toMutableList()
        currentSkills.add(skill)
        userStrengthsFlow.update {
            currentSkills
        }
    }

    override suspend fun removeInterestSkill(skill: Skill) {
        val currentSkills = userInterestedSkillsFlow.value.toMutableList()
        currentSkills.remove(skill)
        userInterestedSkillsFlow.update {
            currentSkills
        }
    }

    override suspend fun removeStrength(skill: Skill) {
        val currentSkills = userStrengthsFlow.value.toMutableList()
        currentSkills.remove(skill)
        userStrengthsFlow.update {
            currentSkills
        }
    }

    override suspend fun setInterestedSkills() {

    }

    override suspend fun setStrengths() {
        TODO("Not yet implemented")
    }
}