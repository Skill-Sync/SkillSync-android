package com.ss.skillsync.session.making

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ss.skillsync.domain.usecase.session_making.AddUserToFriendsUseCase
import com.ss.skillsync.domain.usecase.session_making.ApproveMatchUseCase
import com.ss.skillsync.domain.usecase.session_making.DisconnectSessionMakingUseCase
import com.ss.skillsync.domain.usecase.session_making.GetSessionMakingEventsFlowUseCase
import com.ss.skillsync.domain.usecase.session_making.RejectMatchUseCase
import com.ss.skillsync.domain.usecase.session_making.StartSearchingUseCase
import com.ss.skillsync.domain.usecase.session_making.StopSearchingUseCase
import com.ss.skillsync.domain.usecase.skills.SearchInterestedSkillsUseCase
import com.ss.skillsync.meeting.api.MeetingEvent
import com.ss.skillsync.meeting.api.MeetingManager
import com.ss.skillsync.model.MatchResult
import com.ss.skillsync.model.SessionMakingEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/14/2023.
 */
@HiltViewModel
class SessionMakingViewModel @Inject constructor(
    private val searchInterestedSkillsUseCase: SearchInterestedSkillsUseCase,
    private val getEventsFlowUseCase: GetSessionMakingEventsFlowUseCase,
    private val startSearchingUseCase: StartSearchingUseCase,
    private val stopSearchingUseCase: StopSearchingUseCase,
    private val acceptMatchUseCase: ApproveMatchUseCase,
    private val rejectMatchUseCase: RejectMatchUseCase,
    private val addToFriendsUseCase: AddUserToFriendsUseCase,
    private val disconnectSessionMakingUseCase: DisconnectSessionMakingUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(SessionMakingState())
    val state = _state.asStateFlow()

    init {
        handleSessionMakingEvents()
    }

    fun onEvent(event: SessionMakingUIEvent) {
        when (event) {
            // SKill Selection events
            is SessionMakingUIEvent.OnSearchQueryChanged -> {
                val query = event.query
                val selectedSkill = _state.value.selectedSkill
                if (selectedSkill != null && query == selectedSkill.name) return

                _state.value = _state.value.copy(searchQuery = query, selectedSkill = null)
                searchSkills(event.query)
            }

            is SessionMakingUIEvent.OnSkillSelected -> {
                _state.value = _state.value.copy(
                    selectedSkill = event.skill,
                    searchQuery = event.skill.name,
                    searchResult = emptyList()
                )
            }

            is SessionMakingUIEvent.OnStartSearchingClicked -> {
                startSearching()
            }

            // Match Searching Events
            is SessionMakingUIEvent.OnStopSearchingClicked -> {
                _state.value = _state.value.copy(isSearching = false)
                stopSearching()
            }
            // Match Found Events
            is SessionMakingUIEvent.OnAcceptMatchClicked -> {
                workOnIO {
                    _state.value = _state.value.copy(acceptMatchEnabled = false)
                    acceptMatchUseCase()
                }
            }

            is SessionMakingUIEvent.OnRejectMatchClicked -> {
                workOnIO {
                    rejectMatchUseCase()
                }
            }

            is SessionMakingUIEvent.OnSessionStarted -> {
                _state.value = _state.value.copy(
                    isSearching = false,
                    isMatchApproved = true,
                    hasJoinedSession = true,
                )
                // joinSession(event.manager)
            }
            // After Session Events
            is SessionMakingUIEvent.OnAddToFriendsClicked -> {
                workOnIO {
                    val matchResult = _state.value.matchResult ?: return@workOnIO
                    addToFriendsUseCase(matchResult.userId)
                }
            }

            is SessionMakingUIEvent.OnLeaveSessionMaking -> {
                workOnIO {
                    _state.value = SessionMakingState()
                    disconnectSessionMakingUseCase()
                }
            }

            // General Event
            is SessionMakingUIEvent.OnDisconnect -> {
                workOnIO {
                    disconnectSessionMakingUseCase()
                }
            }
        }
    }

    private fun searchSkills(query: String) = viewModelScope.launch {
        if (query.isBlank()) return@launch
        val result = searchInterestedSkillsUseCase(query)
        if (result.isSuccess) {
            _state.value = _state.value.copy(searchResult = result.getOrNull() ?: emptyList())
        }
    }

    private fun handleSessionMakingEvents() = viewModelScope.launch(Dispatchers.IO) {
        getEventsFlowUseCase().collect { event ->
            when (event) {
                is SessionMakingEvent.Idle -> {
                    _state.value = _state.value.copy(
                        isSearching = false,
                    )
                }

                is SessionMakingEvent.MatchFound -> {
                    _state.value = _state.value.copy(
                        isSearching = false,
                        matchResult = event.matchResult,
                        userProfileImage = event.currentUser.profilePictureUrl,
                    )
                }

                is SessionMakingEvent.MatchApproved -> {
                   val matchResult = _state.value.matchResult ?: return@collect
                    _state.value = _state.value.copy(
                        isSearching = false,
                        matchResult = matchResult.copy(meetingId = event.sessionToken),
                        isMatchApproved = true,
                    )
                }

                is SessionMakingEvent.MatchRejected -> {
                    startSearching()
                }

                is SessionMakingEvent.Disconnected -> {
                    _state.value = _state.value.copy(
                        isSearching = false,
                        matchResult = null,
                        isMatchApproved = false,
                    )
                }

                is SessionMakingEvent.UnknownError -> {
                    _state.value = _state.value.copy(
                        isSearching = false,
                        unknownError = true
                    )
                }
            }
        }
    }

    private fun startSearching() = workOnIO {
        _state.value = _state.value.copy(
            isSearching = true,
            matchResult = null,
        )
        val skill = _state.value.selectedSkill ?: return@workOnIO
        startSearchingUseCase(skill)
    }

    private fun stopSearching() = workOnIO {
        _state.value = _state.value.copy(
            isSearching = false,
            matchResult = null,
            searchQuery = _state.value.selectedSkill?.name ?: "",
            searchResult = emptyList(),
        )
        stopSearchingUseCase()
    }

    private fun joinSession(manager: MeetingManager) {
        manager.joinMeeting(_state.value.matchResult!!.meetingId!!)
        viewModelScope.launch {
            manager.eventsFlow.collect { event ->
                when (event) {
                    MeetingEvent.USER_DISCONNECTED, MeetingEvent.MEETING_ENDED -> {
                        _state.value = _state.value.copy(hasJoinedSession = true)
                    }
                    else -> {}
                }
            }
        }
    }

    private fun workOnIO(block: suspend () -> Unit) = viewModelScope.launch(Dispatchers.IO) {
        block()
    }

}

val fakeMatchResult = MatchResult(
    userId = "1asdqw",
    name = "Muhammed Salman",
    profilePictureUrl = "https://avatars.githubusercontent.com/u/17090794?v=4",
    matchedSkill = com.ss.skillsync.model.Skill(
        id = "1",
        name = "Android",
    )
)
