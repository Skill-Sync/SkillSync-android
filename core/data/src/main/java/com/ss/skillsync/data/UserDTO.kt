package com.ss.skillsync.data

import com.ss.skillsync.model.Skill

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 08/09/2023
 */
class UserDTO(
    val uid: Long = -1,
    val accessToken: String = "",
    val refreshToken: String = "",
    val name: String = "",
    val email: String = "",
    val about: String = "",
    val profilePictureUrl: String = "",
    val onboardingCompleted: Boolean = false,
    val interestedSkills: List<Skill> = emptyList(),
    val strengths: List<Skill> = emptyList(),
)