package com.ss.skillsync

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/7/2023.
 */
@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    init {
        fetchSettings()
    }

    private val _isFirstOpen = MutableStateFlow(false)
    val isFirstOpen = _isFirstOpen.asStateFlow()

    var isAppReady: Boolean = false
        private set

    private fun fetchSettings() = viewModelScope.launch {
        // TODO fetch settings from domain layer
        delay(3000)
        _isFirstOpen.value = true
        isAppReady = true
    }
}