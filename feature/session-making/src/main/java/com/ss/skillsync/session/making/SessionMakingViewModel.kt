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
import com.ss.skillsync.meeting.api.MeetingManager
import com.ss.skillsync.model.MatchResult
import com.ss.skillsync.model.SessionMakingEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
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
        // handleSessionMakingEvents()
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
                    searchResult = emptyList(),
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
                    // acceptMatchUseCase()
                    val matchResult = _state.value.matchResult ?: return@workOnIO
                    _state.value = _state.value.copy(
                        isSearching = false,
                        matchResult = matchResult.copy(meetingId = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJvcmdJZCI6ImNhOWJiNzczLWZhYTMtNDk4My1hN2E0LWQwY2RkZDA3ODE4ZCIsIm1lZXRpbmdJZCI6ImJiYmRjOWUwLWVmY2YtNDYwOC04MWMzLTkxZDM5NDZkNDcyOSIsInBhcnRpY2lwYW50SWQiOiJhYWFmZjE2MS0xNjI5LTQ3ZjctYWMyNC1mMTRhMzQ3YjQ0ZWIiLCJwcmVzZXRJZCI6ImM2NGFjMWIwLWI2ZjctNGU5MS1hOTRlLTFlOGYxZWQ2ZGQ0NyIsImlhdCI6MTY5NDg1Nzc1NiwiZXhwIjoxNzAzNDk3NzU2fQ.Awk5ZKm-RtmmIfJKzWh2wQzAOyFkAvcRFZdrIFLdXqn7nm1gDLexg7LqrtxKFf1QWahHTbtdMq_e4NG8M-7twBMM2bFo-7aoZ8oijvOtaNnY-p82HOgsziLlEJFwi_qf1b87KeYyiQlI1zKSDCL8sHaFSJJQMQV7WT5RBoxFqv6cQVErDMQeXSLHEcfv-TGNP-GGORT7q_drnewNAiN_nU5ni2EMPO4vF8h1kLGSakKY8o9o_pQPbQKKyAf-Zt3ByVvyTqQvqsEB07iGqMTBlquh7yPfsdG9NKmaVluQ-jy7Z3hoiws2Fuja5JIAt0YBdW6mmFZ1tyZhWcjEgeQ3Nw"),
                        isMatchApproved = true,
                    )
                }
            }

            is SessionMakingUIEvent.OnRejectMatchClicked -> {
                workOnIO {
                    // rejectMatchUseCase()
                }
            }

            is SessionMakingUIEvent.OnSessionStarted -> {
                joinSession(event.manager)
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
                    _state.value = _state.value.copy(
                        isSearching = false,
                        matchResult = null,
                        isMatchApproved = false,
                        hasJoinedSession = false,
                    )
                    // disconnectSessionMakingUseCase()
                }
            }

            // General Event
            is SessionMakingUIEvent.OnDisconnect -> {
                workOnIO {
                    // disconnectSessionMakingUseCase()
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

    private fun handleSessionMakingEvents() = workOnIO {
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
        delay(3000)
        val skill = _state.value.selectedSkill ?: return@workOnIO
        // startSearchingUseCase(skill)
        _state.value = _state.value.copy(
            isSearching = false,
            matchResult = fakeMatchResult,
            userProfileImage = fakeMatchResult.profilePictureUrl,
        )
    }

    private fun stopSearching() = workOnIO {
        _state.value = _state.value.copy(
            isSearching = false,
            matchResult = null,
            searchQuery = _state.value.selectedSkill?.name ?: "",
            searchResult = emptyList(),
        )
        // stopSearchingUseCase()
    }

    private fun joinSession(manager: MeetingManager) {
        viewModelScope.launch {
            _state.value = _state.value.copy(
                isSearching = false,
                hasJoinedSession = true,
            )
            manager.joinMeeting(_state.value.matchResult!!.meetingId!!)
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
        id = "1gfg",
        name = "Android",
    )
)
