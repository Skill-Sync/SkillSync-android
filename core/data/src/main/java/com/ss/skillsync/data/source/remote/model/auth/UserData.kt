package com.ss.skillsync.data.source.remote.model.auth

import com.ss.skillsync.data.source.remote.model.skill.SkillData
import com.ss.skillsync.data.source.remote.model.skill.SkillLearned


data class UserData(
    val _id: String?,
    val id: String?,
    val about: String?,
    val active: Boolean?,
    val email: String?,
    val isEmployed: Boolean?,
    val onboarding_completed: Boolean?,
    val name: String?,
    val role: String?,
    val skillsLearned: List<SkillLearned>?,
    val skillsToLearn: List<SkillData>?,
    val accessJWT: String?,
    val refreshJWT: String?,
    val photo: String?
) {
    fun tokensAvailable() = !accessJWT.isNullOrEmpty() && !refreshJWT.isNullOrEmpty()
}