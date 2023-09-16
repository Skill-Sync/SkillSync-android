package com.ss.skillsync.model

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/8/2023.
 */
data class NavigationParams(
    val isFirstTime: Boolean = false,
    val isUserActive: Boolean = false,
    val isOnboardingComplete: Boolean = false,
    val userImage: String? = null,
)
