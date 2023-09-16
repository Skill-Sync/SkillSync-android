package com.ss.skillsync.session.making

import com.ss.skillsync.meeting.api.MeetingManager
import com.ss.skillsync.model.Skill

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/14/2023.
 */
sealed class SessionMakingUIEvent {
    data class OnSearchQueryChanged(val query: String) : SessionMakingUIEvent()
    data class OnSkillSelected(val skill: Skill) : SessionMakingUIEvent()
    data object OnStartSearchingClicked : SessionMakingUIEvent()
    data object OnStopSearchingClicked : SessionMakingUIEvent()

    data object OnAcceptMatchClicked : SessionMakingUIEvent()

    data object OnRejectMatchClicked : SessionMakingUIEvent()

    data class OnSessionStarted(val manager: MeetingManager) : SessionMakingUIEvent()

    data object OnAddToFriendsClicked : SessionMakingUIEvent()

    data object OnLeaveSessionMaking : SessionMakingUIEvent()
    data object OnDisconnect : SessionMakingUIEvent()
}
