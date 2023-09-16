package com.ss.skillsync.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ss.skillsync.domain.usecase.SelectMentorUseCase
import com.ss.skillsync.domain.usecase.auth.GetActiveUserUseCase
import com.ss.skillsync.domain.usecase.session.GetRecommendedMentorsUseCase
import com.ss.skillsync.domain.usecase.session.GetScheduledSessionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 10/09/2023
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getSuggestedMentorsUseCase: GetRecommendedMentorsUseCase,
    private val getScheduledSessionsUseCase: GetScheduledSessionsUseCase,
    private val getActiveUserUseCase: GetActiveUserUseCase,
    private val selectMentorUseCase: SelectMentorUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        loadData()
    }

    fun resetEvents() {
        _state.value = _state.value.copy(
            error = null,
            navDestination = null,
        )
    }

    fun onEvent(homeEvent: HomeEvent) {
        when (homeEvent) {
            is HomeEvent.OnMentorClicked -> {
                selectMentorUseCase(homeEvent.mentor)
                navigateTo(HomeNavDestinations.MentorProfile)
            }

            is HomeEvent.OnSessionClicked -> {
                _state.value = state.value.copy(
                    selectedSession = homeEvent.session
                )
            }

            HomeEvent.OnMatchClicked -> {
                navigateTo(HomeNavDestinations.Match)
            }

            HomeEvent.OnProfileClicked -> {
                navigateTo(HomeNavDestinations.Profile)
            }

            HomeEvent.OnRefresh -> {
                loadData()
            }

            HomeEvent.OnSettingsClicked -> {
                navigateTo(HomeNavDestinations.Settings)
            }

            HomeEvent.OnSessionDismissed -> {
                _state.value = state.value.copy(
                    selectedSession = null
                )
            }
        }
    }

    private fun loadData() = viewModelScope.launch(Dispatchers.IO) {
        val scheduledSessions = async { getScheduledSessionsUseCase().getOrDefault(emptyList()) }
        val activeUser = async { getActiveUserUseCase() }
        val suggestedMentors = async { getSuggestedMentorsUseCase() }
        _state.value = _state.value.copy(
            scheduledSessions = scheduledSessions.await(),
            suggestedMentors = suggestedMentors.await(),
        )
        activeUser.await().onSuccess {
            _state.value = _state.value.copy(activeUser = it)
        }.onFailure {
            _state.value = _state.value.copy(error = it)
        }
        _state.value = _state.value.copy(isLoading = false)
    }

    private fun navigateTo(navDestination: HomeNavDestinations) {
        _state.value = _state.value.copy(navDestination = navDestination)
    }
}

