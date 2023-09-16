package com.ss.skillsync.model

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/14/2023.
 */
data class MatchResult(
    val userId: String,
    val name: String,
    val meetingId: String? = null,
    val profilePictureUrl: String,
    val matchedSkill: Skill,
)
