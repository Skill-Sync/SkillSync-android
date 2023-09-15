package com.ss.skillsync.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 14/09/2023
 */

val Context.settingsDataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingsPreferences(context: Context) {
    private val dataStore = context.settingsDataStore

    companion object Keys {
        private val isDarkModeKey = booleanPreferencesKey("is_dark_mode")
        private val isNotificationsEnabled = booleanPreferencesKey("is_notifications_enabled")
    }

    suspend fun isDarkMode(): Boolean {
        val preferences = dataStore.data.first()
        return preferences[isDarkModeKey] ?: false
    }

    suspend fun setDarkMode(isDarkMode: Boolean) {
        dataStore.edit { preferences ->
            preferences[isDarkModeKey] = isDarkMode
        }
    }

    suspend fun isNotificationsEnabled(): Boolean {
        val preferences = dataStore.data.first()
        return preferences[isNotificationsEnabled] ?: false
    }

    suspend fun setNotificationsEnabled(enabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[isNotificationsEnabled] = enabled
        }
    }
}