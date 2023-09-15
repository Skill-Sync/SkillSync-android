package com.ss.skillsync.friends

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ss.skillsync.domain.usecase.GetUserFriendsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/15/2023.
 */
@HiltViewModel
class FriendsViewModel @Inject constructor(
    private val getUserFriendsUseCase: GetUserFriendsUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(FriendsState())
    val state = _state.asStateFlow()

    init {
        loadFriends()
    }


    private fun loadFriends() = viewModelScope.launch {
        _state.value = FriendsState(isLoading = true)
        getUserFriendsUseCase()
            .onSuccess {
                _state.value = FriendsState(friends = it, isLoading = false)
            }.onFailure {
                _state.value = FriendsState(failedRetrievingFriends = true, isLoading = false)
            }
    }
}