package com.ss.skillsync.editprofile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ss.skillsync.domain.usecase.auth.GetActiveUserUseCase
import com.ss.skillsync.domain.usecase.settings.UpdateProfileUseCase
import com.ss.skillsync.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 14/09/2023
 */

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val getActiveUserUseCase: GetActiveUserUseCase,
    private val updateProfileUseCase: UpdateProfileUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(EditProfileState())
    val state = _state.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            getActiveUserUseCase().onFailure {
                _state.value = _state.value.copy(
                    error = it,
                    isLoading = false,
                )
            }.onSuccess {
                _state.value = _state.value.copy(
                    updatedUser = it,
                    isLoading = false,
                )
            }
        }
    }

    fun resetEvents() {
        _state.value = _state.value.copy(
            backPressed = false,
            error = null,
            navigationDestination = null,
        )
    }

    fun onEvent(event: EditProfileEvent) {
        when (event) {
            EditProfileEvent.BackPressed -> {
                _state.value = _state.value.copy(
                    backPressed = true,
                )
            }

            EditProfileEvent.SaveProfile -> {
                viewModelScope.launch {
                    _state.value = _state.value.copy(
                        isLoading = true,
                    )
                    updateProfileUseCase(_state.value.updatedUser).onFailure {
                        _state.value = _state.value.copy(
                            error = it,
                            isLoading = false,
                        )
                    }.onSuccess {
                        _state.value = _state.value.copy(
                            navigationDestination = EditProfileDestination.Onboarding,
                            isLoading = false,
                        )
                    }
                }
            }

            EditProfileEvent.EditSkills -> {
                _state.value = _state.value.copy(
                    navigationDestination = EditProfileDestination.Onboarding,
                )
            }

            is EditProfileEvent.UpdateUser -> {
                _state.value = _state.value.copy(
                    updatedUser = User(
                        name = event.updatableUser.name ?: _state.value.updatedUser.name,
                        email = event.updatableUser.email ?: _state.value.updatedUser.email,
                        about = event.updatableUser.about ?: _state.value.updatedUser.about,
                        interestedSkills = event.updatableUser.interestedSkills
                            ?: _state.value.updatedUser.interestedSkills,
                        strengths = event.updatableUser.strengths
                            ?: _state.value.updatedUser.strengths,
                    ),
                )
            }
        }
    }
}