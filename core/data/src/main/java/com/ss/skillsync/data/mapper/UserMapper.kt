package com.ss.skillsync.data.mapper

import com.ss.skillsync.data.source.remote.model.auth.UserData
import com.ss.skillsync.domain.util.StringUtil
import com.ss.skillsync.model.Mentor
import com.ss.skillsync.model.User

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/10/2023.
 */

fun UserData.toDomain(): User {
    val image = if (StringUtil.isUrl(photo)) {
        photo!!
    } else {
        StringUtil.getRandomImageUrl()
    }
    val authToken = "Bearer $accessJWT $refreshJWT"
    return User(
        id = id ?: "",
        name = name ?: "",
        email = email ?: "",
        about = about ?: "",
        profilePictureUrl = image,
        authToken = authToken,
        onboardingCompleted = onboardingCompleted ?: false,
        interestedSkills = skillsToLearn?.map { it.toSkill() } ?: emptyList(),
        strengths = skillsLearned?.map { it.toSkill() } ?: emptyList(),
    )
}

fun UserData.toMentor(): Mentor {
    val user = toDomain()
    return with(user) {
        Mentor(
            id = id ?: "",
            name = name,
            email = email,
            field = skill?.name ?: "",
            pictureUrl = profilePictureUrl,
            about = about,
        )
    }
}

fun User.toUserData(): UserData {
    return UserData(
        id = null,
        _id = null,
        email = email,
        name = name,
        about = about,
        skillsToLearn = null,
        skillsLearned = null,
        active = null,
        isEmployed = null,
        onboardingCompleted = null,
        role = null,
        skill = null,
        accessJWT = null,
        refreshJWT = null,
        photo = null,
    )
}

