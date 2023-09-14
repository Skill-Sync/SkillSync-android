package com.ss.skillsync.editprofile

import com.ss.skillsync.model.Skill

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 14/09/2023
 */
sealed class EditProfileEvent {
    data object SaveProfile : EditProfileEvent()
    data object BackPressed : EditProfileEvent()
    data object EditSkills : EditProfileEvent()
    data class UpdateUser(val updatableUser: UpdatableUser) : EditProfileEvent()
}

data class UpdatableUser(
    val name: String? = null,
    val email: String? = null,
    val about: String? = null,
    val interestedSkills: List<Skill>? = null,
    val strengths: List<Skill>? = null,
)