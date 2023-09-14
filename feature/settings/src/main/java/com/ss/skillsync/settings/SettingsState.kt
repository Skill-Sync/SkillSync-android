package com.ss.skillsync.settings

import com.ss.skillsync.model.Settings
import com.ss.skillsync.model.User


/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 14/09/2023
 */
data class SettingsState(
    val activeUser: User = User(),
    val settings: Settings = Settings(),
    val navigationDestination: SettingsDestinations? = null,
    val navigatedUp: Boolean = false,
    val error: Throwable? = null,
)

sealed class SettingsDestinations {
    data object SignInScreen : SettingsDestinations()
    data object PrivacyPolicyScreen : SettingsDestinations()
    data object EditProfileScreen : SettingsDestinations()
}