package com.ss.skillsync.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.ss.skillsync.data.source.remote.model.auth.UserData
import kotlinx.coroutines.flow.first

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 08/09/2023
 */

val Context.userDataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

class UserPreferences(context: Context) {
    private val dataStore = context.userDataStore

    companion object Keys {
        private val accessTokenKey = stringPreferencesKey("access_token")
        private val refreshTokenKey = stringPreferencesKey("refresh_token")
        private val isFirstOpenKey = booleanPreferencesKey("is_first_open")
    }

    suspend fun saveUserTokens(user: UserData) {
        if (user.tokensAvailable()) {
            saveUserTokens(user.accessJWT!!, user.refreshJWT!!)
        }
    }

    suspend fun saveUserTokens(accessToken: String, refreshToken: String) {
        dataStore.edit { preferences ->
            preferences[accessTokenKey] = accessToken
            preferences[refreshTokenKey] = refreshToken
        }
    }

    suspend fun isFirstOpen(): Boolean {
        val preferences = dataStore.data.first()
        return (preferences[isFirstOpenKey] ?: true).also {
            dataStore.edit { preferences ->
                preferences[isFirstOpenKey] = false
            }
        }
    }

    suspend fun getUserToken(): String {
        val preferences = dataStore.data.first()
        val accessToken = preferences.getNotNullString(accessTokenKey)
        val refreshToken = preferences.getNotNullString(refreshTokenKey)
        return "Bearer $accessToken $refreshToken"
    }

    suspend fun clearUserTokens() {
        dataStore.edit { preferences ->
            preferences[accessTokenKey] = ""
            preferences[refreshTokenKey] = ""
        }
    }

    suspend fun areTokensAvailable(): Boolean {
        return try {
            getUserToken()
            true
        } catch (e: Exception) {
            false
        }
    }

    private fun Preferences.getNotNullString(key: Preferences.Key<String>): String {
        return this[key].orEmpty()
            .ifEmpty { throw Exception("Access token not found") }
    }
}
