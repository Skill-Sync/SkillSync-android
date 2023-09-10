package com.ss.skillsync.home

import com.ss.skillsync.model.Mentor

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 10/09/2023
 */
sealed class HomeEvent {
    data class OnMentorClicked(val mentor: Mentor) : HomeEvent()
}