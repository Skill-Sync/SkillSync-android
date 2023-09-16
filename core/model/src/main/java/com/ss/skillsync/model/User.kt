package com.ss.skillsync.model

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 07/09/2023
 */

data class User(
    val id: String = "",
    val name: String = "",
    val email: String = "",
    val about: String = "",
    val authToken: String = "",
    val profilePictureUrl: String = "",
    val onboardingCompleted: Boolean = false,
    val interestedSkills: List<Skill> = emptyList(),
    val strengths: List<Skill> = emptyList(),
)
