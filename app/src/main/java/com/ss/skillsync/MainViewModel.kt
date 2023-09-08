package com.ss.skillsync

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ss.skillsync.domain.usecase.GetNavigationParamsUseCase
import com.ss.skillsync.model.NavigationParams
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/7/2023.
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val getNavigationParamsUseCase: GetNavigationParamsUseCase,
) : ViewModel() {

    init {
        fetchSettings()
    }

    private val _navParams = MutableStateFlow<NavigationParams?>(null)
    val navigationParams = _navParams.asStateFlow()

    var isAppReady: Boolean = false
        private set

    private fun fetchSettings() = viewModelScope.launch {
        getNavigationParamsUseCase().onSuccess {
            _navParams.value = it
        }
        isAppReady = true
    }
}