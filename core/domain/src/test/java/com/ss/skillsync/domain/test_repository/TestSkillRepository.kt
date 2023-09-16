package com.ss.skillsync.domain.test_repository

import com.ss.skillsync.domain.fake.FakeSkillRepository
import com.ss.skillsync.domain.repository.SkillRepository
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
}
