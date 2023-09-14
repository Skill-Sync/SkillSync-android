package com.ss.skillsync.session.making

import com.ss.skillsync.model.Skill

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/14/2023.
 */
sealed class SessionMakingEvent {
    data class OnSearchQueryChanged(val query: String) : SessionMakingEvent()
    data class OnSkillSelected(val skill: Skill) : SessionMakingEvent()
    data object OnStartSearchingClicked : SessionMakingEvent()
    data object OnStopSearchingClicked : SessionMakingEvent()

    data object OnAcceptMatchClicked : SessionMakingEvent()

    data object OnRejectMatchClicked : SessionMakingEvent()

    data object OnAddToFriendsClicked : SessionMakingEvent()

    data object OnLeaveSessionMaking : SessionMakingEvent()
    data object OnDisconnect : SessionMakingEvent()
}
