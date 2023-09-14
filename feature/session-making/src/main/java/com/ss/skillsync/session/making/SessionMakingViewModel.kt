package com.ss.skillsync.session.making

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ss.skillsync.domain.usecase.skills.SearchInterestedSkillsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
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
): ViewModel() {

    private val _state = MutableStateFlow(SessionMakingState())
    val state = _state.asStateFlow()

    fun onEvent(event: SessionMakingEvent) {
        when (event) {
            is SessionMakingEvent.OnSearchQueryChanged -> {
                _state.value = _state.value.copy(searchQuery = event.query)
                searchSkills(event.query)
            }
            is SessionMakingEvent.OnSkillSelected -> {
                _state.value = _state.value.copy(selectedSkill = event.skill)
            }
            is SessionMakingEvent.OnStartSearchingClicked -> {
                _state.value = _state.value.copy(isSearching = true)
                startSearching()
            }
            is SessionMakingEvent.OnStopSearchingClicked -> {
                _state.value = _state.value.copy(isSearching = false)
                stopSearching()
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

    private fun startSearching() {
        // TODO call startSearchingUseCase
    }

    private fun stopSearching() {
        // TODO call stopSearchingUseCase
    }

}
