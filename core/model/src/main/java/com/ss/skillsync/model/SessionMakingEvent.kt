package com.ss.skillsync.model

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/14/2023.
 */
sealed class SessionMakingEvent {

    data object Idle : SessionMakingEvent()
    data class MatchFound(val currentUser: User, val matchResult: MatchResult) : SessionMakingEvent()
    data class MatchApproved(val sessionToken: String) : SessionMakingEvent()
    data object MatchRejected : SessionMakingEvent()
    data object Disconnected : SessionMakingEvent()

    data object UnknownError : SessionMakingEvent()
}