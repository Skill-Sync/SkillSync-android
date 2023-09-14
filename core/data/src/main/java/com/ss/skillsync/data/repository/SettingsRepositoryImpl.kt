package com.ss.skillsync.data.repository

import com.ss.skillsync.data.preferences.SettingsPreferences
import com.ss.skillsync.domain.repository.SettingsRepository
import javax.inject.Inject

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 14/09/2023
 */
class SettingsRepositoryImpl @Inject constructor(
    private val settingsPreferences: SettingsPreferences,
) : SettingsRepository {
    override suspend fun isDarkMode(): Boolean =
        settingsPreferences.isDarkMode()

    override suspend fun setDarkMode(isDarkMode: Boolean) =
        settingsPreferences.setDarkMode(isDarkMode)

    override suspend fun isNotificationsEnabled(): Boolean =
        settingsPreferences.isNotificationsEnabled()

    override suspend fun setNotificationsEnabled(enabled: Boolean) =
        settingsPreferences.setNotificationsEnabled(enabled)
}