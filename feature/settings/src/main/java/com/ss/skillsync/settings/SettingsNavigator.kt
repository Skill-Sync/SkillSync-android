package com.ss.skillsync.settings

import com.ss.skillsync.commonandroid.BaseNavigator

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 14/09/2023
 */
interface SettingsNavigator: BaseNavigator {
    fun navigateToSignInScreen()
    fun navigateToPrivacyPolicyScreen()
    fun navigateToEditProfileScreen()
}