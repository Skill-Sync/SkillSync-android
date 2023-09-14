package com.ss.skillsync.settings

import com.ss.skillsync.model.Settings

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 14/09/2023
 */
sealed class SettingsEvent {
    data object LogoutClicked : SettingsEvent()
    data object PrivacyPolicyClicked : SettingsEvent()
    data object EditProfileClicked : SettingsEvent()
    data object OnBackPressed : SettingsEvent()
    data class SettingsUpdated(val settings: Settings) : SettingsEvent()
}