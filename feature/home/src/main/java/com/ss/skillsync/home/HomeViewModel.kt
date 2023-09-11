package com.ss.skillsync.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ss.skillsync.domain.usecase.home.GetRecommendedMentorsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
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
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = _state.value.copy(
                suggestedMentors = getSuggestedMentorsUseCase()
            )
        }
    }

    fun resetEvents() {
        _state.value = _state.value.copy(
            error = null,
            navDestination = null
        )
    }

    fun onEvent(homeEvent: HomeEvent) {
        when(homeEvent) {
            is HomeEvent.OnMentorClicked -> {
                /* Navigate to mentor profile */
            }
            is HomeEvent.OnSessionClicked -> {
                /* Navigate to session details */
            }
            HomeEvent.OnMatchClicked -> {
                /* Navigate to match screen */
            }
            HomeEvent.OnProfileClicked -> {
                /* Navigate to profile screen */
            }
            HomeEvent.OnRefresh -> {
                /* Refresh sessions scheduled */
            }
            HomeEvent.OnSettingsClicked -> {
                /* Navigate to settings screen */
            }
        }
    }
}

