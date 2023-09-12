package com.ss.skillsync.mentorhome

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 12/09/2023
 */
interface MentorHomeNavigator {
    fun navigateToSignInScreen()
    fun navigateToSessionScreen(sessionId: String)
}