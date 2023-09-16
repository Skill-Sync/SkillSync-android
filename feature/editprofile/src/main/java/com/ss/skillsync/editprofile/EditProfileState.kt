package com.ss.skillsync.editprofile

import com.ss.skillsync.model.User

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 14/09/2023
 */

data class EditProfileState(
    val updatedUser: User = User(),
    val isLoading: Boolean = false,
    val error: Throwable? = null,
    val backPressed: Boolean = false,
    val navigationDestination: EditProfileDestination? = null,
)

sealed class EditProfileDestination {
    data object Onboarding : EditProfileDestination()
}
