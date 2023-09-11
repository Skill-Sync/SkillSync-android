package com.ss.skillsync.data.mapper

import com.ss.skillsync.data.source.remote.model.auth.UserData
import com.ss.skillsync.model.User

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/10/2023.
 */

fun UserData.toDomain(): User {
    val user = User(
        name = name ?: "",
        email = email ?: "",
        about = about ?: "",
        profilePictureUrl = photo ?: "",
        onboardingCompleted = onboardingCompleted ?: false,
        interestedSkills = skillsToLearn?.map { it.toSkill() } ?: emptyList(),
        strengths = skillsLearned?.map { it.toSkill() } ?: emptyList()
    )
    return user
}