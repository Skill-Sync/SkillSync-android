package com.ss.skillsync.mentorhome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ss.skillsync.domain.usecase.auth.GetActiveMentorUseCase
import com.ss.skillsync.domain.usecase.auth.SignOutUseCase
import com.ss.skillsync.domain.usecase.session.GetScheduledSessionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 12/09/2023
 */
@HiltViewModel
class MentorHomeViewModel @Inject constructor(
    private val getActiveMentorUseCase: GetActiveMentorUseCase,
    private val getScheduledSessionsUseCase: GetScheduledSessionsUseCase,
    private val signOutUseCase: SignOutUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(MentorHomeState())
    val state = _state.asStateFlow()

    init {
        loadData()
    }

    fun onEvent(event: MentorHomeEvent) {
        when (event) {
            MentorHomeEvent.OnLogoutClicked -> {
                viewModelScope.launch {
                    signOutUseCase()
                    _state.value = _state.value.copy(
                        activeUser = null,
                        sessions = emptyList(),
                        navigationDestination = MentorHomeDestination.SignInScreen
                    )
                }
            }
            MentorHomeEvent.OnRefresh -> loadData()
            is MentorHomeEvent.OnSessionClicked -> {
                _state.value = _state.value.copy(
                    navigationDestination = MentorHomeDestination.SessionScreen(event.sessionId)
                )
            }
        }
    }

    private fun loadData() {
        viewModelScope.launch {
            _state.value = MentorHomeState(isLoading = true)
            getActiveMentorUseCase().onSuccess {
                _state.value = _state.value.copy(activeUser = it)
            }.onFailure {
                _state.value = _state.value.copy(error = it.message)
            }
        }
        viewModelScope.launch {
            getScheduledSessionsUseCase().onSuccess {
                _state.value = _state.value.copy(sessions = it)
            }.onFailure {
                _state.value = _state.value.copy(error = it.message)
            }
        }
    }
}