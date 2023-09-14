package com.ss.skillsync.domain.repository

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 14/09/2023
 */
interface SettingsRepository {
    suspend fun isDarkMode(): Boolean
    suspend fun setDarkMode(isDarkMode: Boolean)
    suspend fun isNotificationsEnabled(): Boolean
    suspend fun setNotificationsEnabled(enabled: Boolean)
}