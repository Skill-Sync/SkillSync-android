package com.ss.skillsync.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ss.skillsync.domain.usecase.SignupUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 8/30/2023.
 */
@HiltViewModel
class SignupViewModel @Inject constructor(
    private val signupUseCase: SignupUseCase
): ViewModel() {

    private var _state = MutableStateFlow(SignupState())
    val state = _state.asStateFlow()

    fun onFullNameChanged(fullName: String) {
        _state.value = _state.value.copy(fullName = fullName)
    }
    fun onEmailChanged(email: String) {
        _state.value = _state.value.copy(email = email)
    }

    fun onPasswordChanged(password: String) {
        _state.value = _state.value.copy(password = password)
    }

    fun onConfirmPasswordChanged(confirmPassword: String) {
        _state.value = _state.value.copy(confirmPassword = confirmPassword)
    }

    fun onSignupClicked() = viewModelScope.launch {
        val state = _state.value
        if (state.isLoading) return@launch
        _state.value = state.copy(isLoading = true)
        signup(state)
    }

    fun reset() {
        _state.value = SignupState()
    }

    fun resetError() {
        _state.value = _state.value.copy(error = null)
    }

    private suspend fun signup(state: SignupState) {
        signupUseCase(
            state.fullName,
            state.email,
            state.password,
            state.confirmPassword
        ).onSuccess {
            _state.value = state.copy(isSignupSuccessful = true, isLoading = false)
        }.onFailure {
            _state.value = state.copy(error = it.message, isLoading = false)
        }
    }

}