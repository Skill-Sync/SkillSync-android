package com.ss.skillsync.profile.mentor

import com.ss.skillsync.model.Day
import com.ss.skillsync.model.Session

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/11/2023.
 */
data class ProfileState(
    val name: String = "",
    val skill: String = "",
    val imageUrl: String? = null,
    val about: String = "",
    val daySessions: UISessionDays? = null,
    val selectedDay: Day? = null,
    val selectedSession: Session? = null,
    val failedToLoadProfile: Boolean = false,
    val failedToLoadSessions: Boolean = false,
    val isLoading: Boolean = false,
    val isSessionBookedSuccessfully: Boolean = false,
    val failedToBookSession: Boolean = false,
    val navDestination: ProfileNavDestination? = null
) {
    val isProfileLoaded: Boolean
        get() = name.isNotEmpty() && skill.isNotEmpty() && imageUrl != null

    val isBookSessionEnabled: Boolean
        get() = selectedDay != null && selectedSession != null
}

sealed class ProfileNavDestination {
    data object Back : ProfileNavDestination()
}
