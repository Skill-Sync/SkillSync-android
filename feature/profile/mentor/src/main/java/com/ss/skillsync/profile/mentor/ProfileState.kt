package com.ss.skillsync.profile.mentor

import com.ss.skillsync.model.Session

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/11/2023.
 */
data class ProfileState(
    val name: String = "",
    val skill: String = "",
    val imageUrl: String? = null,
    val sessions: List<Session> = emptyList(),
    val failedToLoadProfile: Boolean = false,
    val failedToLoadSessions: Boolean = false,
    val isLoading: Boolean = false
) {
    val isProfileLoaded: Boolean
        get() = name.isNotEmpty() && skill.isNotEmpty() && imageUrl != null
}
