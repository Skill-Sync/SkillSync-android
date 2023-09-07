package com.ss.skillsync.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 8/30/2023.
 */
@HiltViewModel
class SignupViewModel @Inject constructor(): ViewModel() {

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

    fun onSignupClicked() = viewModelScope.launch(Dispatchers.IO) {
        // TODO call UserRepository.signup()
        delay(2000)
        _state.value = _state.value.copy(isSignupSuccessful = true)
    }

    fun reset() {
        _state.value = SignupState()
    }

}