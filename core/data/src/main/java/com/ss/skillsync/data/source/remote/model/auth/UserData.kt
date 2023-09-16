package com.ss.skillsync.data.source.remote.model.auth

import com.google.gson.annotations.SerializedName
import com.ss.skillsync.data.source.remote.model.skill.SkillData
import com.ss.skillsync.data.source.remote.model.skill.SkillLearned

data class UserData(
    val _id: String?,
    val id: String?,
    val about: String?,
    val active: Boolean?,
    val email: String?,
    val isEmployed: Boolean?,
    @SerializedName("onboarding_completed")
    val onboardingCompleted: Boolean?,
    val name: String?,
    val role: String?,
    val skill: SkillData?,
    val skillsLearned: List<SkillLearned>?,
    val skillsToLearn: List<SkillData>?,
    val accessJWT: String?,
    val refreshJWT: String?,
    val photo: String?,
) {
    fun tokensAvailable() = !accessJWT.isNullOrEmpty() && !refreshJWT.isNullOrEmpty()
}