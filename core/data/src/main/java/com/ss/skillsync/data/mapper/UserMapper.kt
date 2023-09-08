package com.ss.skillsync.data.mapper

import com.ss.skillsync.data.UserDTO
import com.ss.skillsync.model.User

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 08/09/2023
 */

fun UserDTO.toDomainModel(): User =
    User(
        name = name,
        email = email,
        about = about,
        profilePictureUrl = profilePictureUrl,
        onboardingCompleted = onboardingCompleted,
        interestedSkills = interestedSkills,
        strengths = strengths,
    )
