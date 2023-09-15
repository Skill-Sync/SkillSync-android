package com.ss.skillsync.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ss.skillsync.domain.usecase.GetNavigationParamsUseCase
import com.ss.skillsync.domain.usecase.auth.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 8/30/2023.
 */
@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val getNavigationParamsUseCase: GetNavigationParamsUseCase,
) : ViewModel() {

    private var _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    init {
        loadNavParams()
    }

    fun onEmailChanged(email: String) {
        _state.value = _state.value.copy(email = email)
    }

    fun onPasswordChanged(password: String) {
        _state.value = _state.value.copy(password = password)
    }

    fun onTypeChanged() {
        val currentType = _state.value.loginType
        val type = if (currentType == "user") "mentor" else "user"
        _state.value = _state.value.copy(loginType = type)
    }

    fun onSignInClicked() = viewModelScope.launch {
        val state = _state.value
        if (state.isLoading) return@launch
        _state.value = state.copy(isLoading = true)
        signIn(state)
    }

    fun resetErrors() {
        _state.value = _state.value.copy(error = null)
    }

    private suspend fun signIn(state: SignInState) {
        signInUseCase(
            state.email,
            state.password,
            state.loginType
        ).onSuccess {
            _state.value = state.copy(
                isSignInSuccessful = true,
                isLoading = false,
                isOnboardingCompleted = it.onboardingCompleted
            )
        }.onFailure {
            _state.value = state.copy(error = it.message, isLoading = false)
        }
    }

    private fun loadNavParams() = viewModelScope.launch {
        getNavigationParamsUseCase().onSuccess {
            _state.value = _state.value.copy(isFirstOpen = it.isFirstTime)
        }
    }
}
