package com.ss.skillsync.mentorhome

import com.ss.skillsync.model.Mentor
import com.ss.skillsync.model.Session

data class MentorHomeState(
    val sessions: List<Session> = emptyList(),
    val activeUser: Mentor? = Mentor(),
    val isLoading: Boolean = true,
    val error: String? = null,
    val navigationDestination: MentorHomeDestination? = null,
)

sealed class MentorHomeDestination {
    data object SignInScreen : MentorHomeDestination()
    data class SessionScreen(val sessionId: String) : MentorHomeDestination()
}
