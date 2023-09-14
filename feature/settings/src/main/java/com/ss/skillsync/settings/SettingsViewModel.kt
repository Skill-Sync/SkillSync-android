package com.ss.skillsync.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ss.skillsync.domain.usecase.auth.SignOutUseCase
import com.ss.skillsync.domain.usecase.settings.GetSettingsUseCase
import com.ss.skillsync.domain.usecase.settings.UpdateSettingsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 14/09/2023
 */
class SettingsViewModel @Inject constructor(
    private val signOutUseCase: SignOutUseCase,
    private val updateSettingsUseCase: UpdateSettingsUseCase,
    private val getSettingsUseCase: GetSettingsUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(SettingsState())
    val state = _state.asStateFlow()

    fun resetEvents() {
        _state.value = _state.value.copy(
            navigationDestination = null,
            navigatedUp = false,
            error = null,
        )
    }

    fun onEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.LogoutClicked -> {
                viewModelScope.launch {
                    signOutUseCase()
                }
                _state.value = _state.value.copy(
                    navigationDestination = SettingsDestinations.SignInScreen
                )
            }

            is SettingsEvent.PrivacyPolicyClicked -> {
                _state.value = _state.value.copy(
                    navigationDestination = SettingsDestinations.PrivacyPolicyScreen
                )
            }

            is SettingsEvent.OnBackPressed -> {
                _state.value = _state.value.copy(
                    navigatedUp = true
                )
            }

            SettingsEvent.EditProfileClicked -> {
                _state.value = _state.value.copy(
                    navigationDestination = SettingsDestinations.EditProfileScreen
                )
            }
            is SettingsEvent.SettingsUpdated -> {
                _state.value = _state.value.copy(
                    settings = event.settings
                )
            }
        }
    }
}