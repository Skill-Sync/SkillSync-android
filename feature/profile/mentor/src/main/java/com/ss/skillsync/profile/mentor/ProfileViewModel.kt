package com.ss.skillsync.profile.mentor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ss.skillsync.domain.usecase.GetSelectedMentorUseCase
import com.ss.skillsync.domain.usecase.session.GetMentorSessionDaysUseCase
import com.ss.skillsync.domain.usecase.session.ScheduleSessionUseCase
import com.ss.skillsync.model.Mentor
import com.ss.skillsync.model.SessionDays
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/11/2023.
 */
@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getSelectedMentorUseCase: GetSelectedMentorUseCase,
    private val getSessionDaysUseCae: GetMentorSessionDaysUseCase,
    private val scheduleSessionUseCase: ScheduleSessionUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(ProfileState())
    val state = _state.asStateFlow()

    init {
        loadData()
    }

    fun onEvent(profileEvent: ProfileEvent) {
        when (profileEvent) {
            is ProfileEvent.OnDayClicked -> {
                _state.value = _state.value.copy(
                    selectedDay = profileEvent.day,
                    selectedSession = null
                )
            }

            is ProfileEvent.OnSessionClicked -> {
                _state.value = _state.value.copy(
                    selectedSession = profileEvent.session
                )
            }

            is ProfileEvent.OnBookSessionClicked -> {
                scheduleSession()
            }

            else -> {}
        }
    }

    private fun loadData() = viewModelScope.launch {
        _state.value = _state.value.copy(
            failedToLoadProfile = false,
            failedToLoadSessions = false,
            isLoading = true
        )
        val mentor = getSelectedMentorUseCase().getOrElse {
            _state.value = _state.value.copy(
                failedToLoadProfile = true
            )
            return@launch
        }
        setMentorState(mentor)
        loadSessionDays(mentor)?.let {
            setSessionDaysState(it)
        } ?: return@launch
    }

    private suspend fun loadSessionDays(mentor: Mentor): SessionDays? {
        _state.value = _state.value.copy(
            failedToLoadSessions = false,
            isLoading = true
        )
        return getSessionDaysUseCae(mentor).getOrElse {
            _state.value = _state.value.copy(
                failedToLoadSessions = true,
                isLoading = false
            )
            null
        }
    }

    private fun scheduleSession() {
        val session = _state.value.selectedSession ?: return
        val mentor = getSelectedMentorUseCase().getOrNull() ?: return
        toggleLoading(true)
        viewModelScope.launch {
            scheduleSessionUseCase(session.sessionId)
                .onSuccess {
                    _state.value = _state.value.copy(
                        isSessionBookedSuccessfully = true,
                    )
                    loadSessionDays(mentor)
                }.onFailure {
                    _state.value = _state.value.copy(
                        failedToBookSession = true
                    )
                }
            toggleLoading(false)
        }
    }

    private fun setMentorState(mentor: Mentor) {
        _state.value = _state.value.copy(
            name = mentor.name,
            skill = mentor.field,
            imageUrl = mentor.pictureUrl,
            about = mentor.about,
            isLoading = false
        )
    }

    private fun setSessionDaysState(sessionDays: SessionDays) {
        val sessionDaysUI = UISessionDays.from(sessionDays)
        _state.value = _state.value.copy(
            daySessions = sessionDaysUI,
            isLoading = false
        )
    }

    private fun toggleLoading(loading: Boolean) {
        _state.value = _state.value.copy(
            isLoading = loading
        )
    }
}