package com.ss.skillsync.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.ss.skillsync.data.UserDTO

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 08/09/2023
 */

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

val accessTokenKey = stringPreferencesKey("access_token")
val refreshTokenKey = stringPreferencesKey("refresh_token")

fun saveUserTokens(userDTO: UserDTO) {
    // TODO: Save user token to DataStore preferences
    /*dataStore.edit { preferences ->
        preferences[accessToken] = userDTO.accessToken
        preferences[refreshToken] = userDTO.refreshToken
    }*/
}

fun getUserToken(): String {
    // TODO: Get user tokens from DataStore preferences
    /*val tokensPairFlow: Flow<Pair<String, String>> = context.dataStore.data.map { preferences ->
        val accessToken = preferences[accessToken] ?: ""
        val refreshToken = preferences[refreshToken] ?: ""
        Pair(accessToken, refreshToken)
    }*/
    val accessToken = "" /* TEMPORARILY */
    val refreshToken = "" /* TEMPORARILY */
    return "Bearer $accessToken $refreshToken" /* TEMPORARILY */
}

fun clearUserTokens() {
    // TODO: Clear user tokens from DataStore preferences
    /*dataStore.edit { preferences ->
        preferences.clear()
    }*/
}