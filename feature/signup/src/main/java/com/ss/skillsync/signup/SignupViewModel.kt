package com.ss.skillsync.signup

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 8/30/2023.
 */
@HiltViewModel
class SignupViewModel @Inject constructor(): ViewModel() {

    private var _state = MutableStateFlow(SignupState())
    val state = _state.asStateFlow()

}