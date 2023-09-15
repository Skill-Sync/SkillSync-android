package com.ss.skillsync.domain.test_repository

import com.ss.skillsync.domain.fake.FakeSkillRepository
import com.ss.skillsync.domain.repository.SkillRepository
import com.ss.skillsync.model.Skill
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/8/2023.
 */
class TestSkillRepository {
    private lateinit var skillRepository: SkillRepository

    @Before
    fun setUp() {
        skillRepository = FakeSkillRepository()
    }

    @Test
    fun testLoadStaticSkills() = runBlocking {
        val result = skillRepository.loadStaticSkills()
        assert(result.isNotEmpty())
    }

    @Test
    fun testSearchSkills() = runBlocking {
        val result = skillRepository.searchSkills("Programming")
        assert(result.isNotEmpty())
    }

    @Test
    fun testAddInterestSkill() = runBlocking {
        val skill = Skill(id = 1, name = "Programming")
        skillRepository.addInterestSkill(skill)
        val userInterestedSkills = skillRepository.userInterestedSkills.first()
        assert(userInterestedSkills.contains(skill))
    }

    @Test
    fun testAddStrength() = runBlocking {
        val skill = Skill(id = 1, name = "Programming")
        skillRepository.addStrength(skill)
        val userStrengths = skillRepository.userStrengths.first()
        assert(userStrengths.contains(skill))
    }

    @Test
    fun testRemoveStrength() = runBlocking {
        val skill = Skill(id = 1, name = "Programming")
        skillRepository.addStrength(skill)
        val userStrengths = skillRepository.userStrengths.first()
        skillRepository.removeStrength(skill)
        val updatedUserStrengths = skillRepository.userStrengths.first()
        assert(userStrengths.contains(skill))
        assert(!updatedUserStrengths.contains(skill))
    }

    @Test
    fun testRemoveInterestedSkill() = runBlocking {
        val skill = Skill(id = 1, name = "Programming")
        skillRepository.addInterestSkill(skill)
        val userInterestedSkills = skillRepository.userInterestedSkills.first()
        skillRepository.removeInterestSkill(skill)
        val updatedUserInterestedSkills = skillRepository.userInterestedSkills.first()
        assert(userInterestedSkills.contains(skill))
        assert(!updatedUserInterestedSkills.contains(skill))
    }
}
