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
    object OnRefresh : HomeEvent()
    object OnMatchClicked : HomeEvent()
    object OnProfileClicked : HomeEvent()
    object OnSettingsClicked : HomeEvent()
}