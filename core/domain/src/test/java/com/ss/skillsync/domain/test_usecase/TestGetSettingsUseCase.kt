package com.ss.skillsync.domain.test_usecase

import com.ss.skillsync.domain.fake.FakeSettingsRepository
import com.ss.skillsync.domain.repository.SettingsRepository
import com.ss.skillsync.domain.usecase.settings.GetSettingsUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/16/2023.
 */
class TestGetSettingsUseCase {

    private lateinit var settingsRepository: SettingsRepository
    private lateinit var getSettingsUseCase: GetSettingsUseCase

    @Before
    fun setup() {
        settingsRepository = FakeSettingsRepository()
        getSettingsUseCase = GetSettingsUseCase(settingsRepository)
    }


    @Test
    fun `GetSettingsUseCase returns settings`() = runBlocking {
        val result = getSettingsUseCase()

        assert(result.darkMode == settingsRepository.isDarkMode())
        assert(result.notificationsEnabled == settingsRepository.isNotificationsEnabled())
    }
}