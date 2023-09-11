package com.ss.skillsync.home

import com.ss.skillsync.model.Mentor
import com.ss.skillsync.model.Session
import com.ss.skillsync.model.User

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 10/09/2023
 */
data class HomeState(
    val suggestedMentors: List<Mentor> = emptyList(),
    val scheduledSessions: List<Session> = emptyList(),
    val activeUser: User = User(),
    val error: Throwable? = null,
    val isLoading: Boolean = true,
)
