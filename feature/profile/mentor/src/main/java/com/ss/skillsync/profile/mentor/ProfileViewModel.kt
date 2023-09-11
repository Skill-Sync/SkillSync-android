package com.ss.skillsync.profile.mentor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ss.skillsync.domain.usecase.GetSelectedMentorUseCase
import com.ss.skillsync.domain.usecase.session.GetMentorSessionsUseCase
import com.ss.skillsync.model.Mentor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/11/2023.
 */
@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getSelectedMentorUseCase: GetSelectedMentorUseCase,
    private val getMentorSessionsUseCase: GetMentorSessionsUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(ProfileState())
    val state = _state.asStateFlow()

    init {
        loadData()
    }

    fun onEvent(profileEvent: ProfileEvent) {
        when (profileEvent) {
            is ProfileEvent.OnSessionClicked -> {
                // navigateTo(HomeNavDestinations.SessionDetails(session = homeEvent.session))
            }

            else -> {}
        }
    }

    private fun loadData() = viewModelScope.launch {
        _state.value = _state.value.copy(
            failedToLoadProfile = false,
            failedToLoadSessions = false,
            isLoading = true
        )

        val mentor = getSelectedMentorUseCase().getOrNull()
        if (mentor == null) {
            _state.value = _state.value.copy(
                failedToLoadProfile = true
            )
            return@launch
        }
        setMentorState(mentor)
        val sessions = getMentorSessionsUseCase(mentor).getOrNull()?.sessions
        sessions?.let {
            _state.value = _state.value.copy(
                sessions = it
            )
        } ?: run {
            _state.value = _state.value.copy(
                failedToLoadSessions = true
            )
        }
    }

    private fun setMentorState(mentor: Mentor) {
        _state.value = _state.value.copy(
            name = mentor.name,
            skill = mentor.field,
            imageUrl = mentor.pictureUrl,
            isLoading = false
        )
    }
}