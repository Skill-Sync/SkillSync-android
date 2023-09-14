package com.ss.skillsync.mentorhome

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 12/09/2023
 */
sealed class MentorHomeEvent {
    data class OnSessionClicked(val sessionId: String) : MentorHomeEvent()
    data object OnLogoutClicked : MentorHomeEvent()
    data object OnRefresh : MentorHomeEvent()
}