package com.ss.skillsync.home

import com.ss.skillsync.model.Mentor
import com.ss.skillsync.model.Session

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 10/09/2023
 */
sealed class HomeEvent {
    data class OnMentorClicked(val mentor: Mentor) : HomeEvent()
    data class OnSessionClicked(val session: Session) : HomeEvent()
    data object OnRefresh : HomeEvent()
    data object OnMatchClicked : HomeEvent()
    data object OnProfileClicked : HomeEvent()
    data object OnSettingsClicked : HomeEvent()
    data object OnSessionDismissed : HomeEvent()
}