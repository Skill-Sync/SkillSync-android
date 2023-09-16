package com.ss.skillsync.domain.test_usecase

import com.ss.skillsync.domain.fake.FakeSettingsRepository
import com.ss.skillsync.domain.repository.SettingsRepository
import com.ss.skillsync.domain.usecase.settings.UpdateSettingsUseCase
import com.ss.skillsync.model.Settings
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/16/2023.
 */
class TestUpdateSettingsUseCase {
    private lateinit var settingsRepository: SettingsRepository
    private lateinit var updateSettingsUseCase: UpdateSettingsUseCase

    @Before
    fun setup() {
        settingsRepository = FakeSettingsRepository()
        updateSettingsUseCase = UpdateSettingsUseCase(settingsRepository)
    }


    @Test
    fun `UpdateSettingsUseCase updates settings`() = runBlocking {
        updateSettingsUseCase(
            Settings(
                darkMode = true,
                notificationsEnabled = true
            )
        )

        assert(settingsRepository.isDarkMode())
        assert(settingsRepository.isNotificationsEnabled())
    }
}