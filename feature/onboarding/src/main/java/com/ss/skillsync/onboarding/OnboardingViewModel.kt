package com.ss.skillsync.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ss.skillsync.domain.usecase.auth.GetActiveUserUseCase
import com.ss.skillsync.domain.usecase.skills.SaveSkillsUseCase
import com.ss.skillsync.domain.usecase.skills.SearchSkillsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 8/30/2023.
 */
@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val searchSkillsUseCase: SearchSkillsUseCase,
    private val saveSkillsUseCase: SaveSkillsUseCase,
    private val getActiveUserUseCase: GetActiveUserUseCase,
) : ViewModel() {

    private var _state = MutableStateFlow(OnboardingState())
    val state = _state.asStateFlow()


    init {
        loadData()
    }

    fun navigatedSuccessfully() {
        _state.value = _state.value.copy(
            onboardingDone = false
        )
    }

    private fun loadData() {
        viewModelScope.launch {
            getActiveUserUseCase().onSuccess {
                _state.value = _state.value.copy(
                    selectedInterests = it.interestedSkills.toMutableSet(),
                    selectedStrengths = it.strengths.toMutableSet(),
                )
            }.onFailure {
                _state.value = _state.value.copy(
                    error = it
                )
            }
        }
    }

    fun onEvent(onboardingEvent: OnboardingEvent) {
        when (onboardingEvent) {
            is OnboardingEvent.FromEditProfile -> {
                _state.value = _state.value.copy(
                    fromEditProfile = true,
                )
            }

            is OnboardingEvent.SearchQueryChanged -> {
                _state.value = _state.value.copy(
                    searchQuery = onboardingEvent.query,
                )
                if (onboardingEvent.query.isEmpty()) {
                    _state.value = _state.value.copy(
                        queryResult = emptySet(),
                    )
                    return
                }
                viewModelScope.launch {
                    searchSkillsUseCase(
                        onboardingEvent.query,
                        _state.value.selectedInterests
                    ).onSuccess {
                        _state.value = _state.value.copy(
                            queryResult = it,
                        )
                    }
                }
            }

            is OnboardingEvent.SkillSelected -> {
                if (_state.value.currentPageIndex == 0)
                    _state.value = _state.value.copy(
                        selectedInterests = _state.value.selectedInterests + onboardingEvent.skill,
                    )
                else if (_state.value.currentPageIndex == 1) {
                    _state.value = _state.value.copy(
                        selectedStrengths = _state.value.selectedStrengths + onboardingEvent.skill,
                    )
                }
            }

            is OnboardingEvent.NextClicked -> {
                _state.value = _state.value.copy(
                    currentPageIndex = _state.value.currentPageIndex + 1,
                    searchQuery = "",
                    queryResult = emptySet(),
                )
            }

            is OnboardingEvent.DoneClicked -> {
                saveSkills()
            }

            is OnboardingEvent.BackClicked -> {
                val currentPageIndex = _state.value.currentPageIndex
                if (currentPageIndex == 0) {
                    _state.value = _state.value.copy(
                        onboardingDone = true
                    )
                } else
                    _state.value = _state.value.copy(
                        currentPageIndex = 0,
                        searchQuery = "",
                    )
            }

            is OnboardingEvent.SkillLevelUpdated -> {
                if (_state.value.currentPageIndex == 1) {
                    val updatedSet = _state.value.selectedStrengths.toMutableList()
                    updatedSet[updatedSet.indexOf(onboardingEvent.skill)] =
                        onboardingEvent.skill.copy(level = onboardingEvent.skillLevel)
                    _state.value = _state.value.copy(
                        selectedStrengths = updatedSet.toMutableSet(),
                    )
                }
            }

            is OnboardingEvent.SkillRemoved -> {
                if (_state.value.currentPageIndex == 0) {
                    _state.value = _state.value.copy(
                        selectedInterests = _state.value.selectedInterests - onboardingEvent.skill,
                    )
                } else if (_state.value.currentPageIndex == 1) {
                    _state.value = _state.value.copy(
                        selectedStrengths = _state.value.selectedStrengths - onboardingEvent.skill,
                    )
                }
            }
        }
    }

    private fun saveSkills() {
        viewModelScope.launch {
            _state.value = _state.value.copy(
                isUpdating = true
            )
            val interestedSkills = _state.value.selectedInterests.toList()
            val strengthSkills = _state.value.selectedStrengths.toList()
            saveSkillsUseCase(interestedSkills, strengthSkills).onSuccess {
                _state.value = _state.value.copy(
                    onboardingDone = true,
                    isUpdating = false
                )
            }
        }
    }
}
