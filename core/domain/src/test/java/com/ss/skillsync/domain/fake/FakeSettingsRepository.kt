package com.ss.skillsync.domain.fake

import com.ss.skillsync.domain.repository.SettingsRepository

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/16/2023.
 */
class FakeSettingsRepository : SettingsRepository {
    companion object {
        const val KEY_DARK_MODE = "dark_mode"
        const val KEY_NOTIFICATIONS_ENABLED = "notifications_enabled"
    }

    private val settingsMap = mutableMapOf<String, Any>()
    override suspend fun isDarkMode(): Boolean {
        return settingsMap[KEY_DARK_MODE] as? Boolean ?: false
    }

    override suspend fun setDarkMode(isDarkMode: Boolean) {
        settingsMap[KEY_DARK_MODE] = isDarkMode
    }

    override suspend fun isNotificationsEnabled(): Boolean {
        return settingsMap[KEY_NOTIFICATIONS_ENABLED] as? Boolean ?: false
    }

    override suspend fun setNotificationsEnabled(enabled: Boolean) {
        settingsMap[KEY_NOTIFICATIONS_ENABLED] = enabled
    }
}