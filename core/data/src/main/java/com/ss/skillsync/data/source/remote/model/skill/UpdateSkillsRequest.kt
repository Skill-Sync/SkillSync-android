package com.ss.skillsync.data.source.remote.model.skill

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/10/2023.
 */
data class UpdateSkillsRequest(
    val skillsToLearn: List<String>,
    val skillsLearned: List<SkillLearnedRequest>,
)

data class SkillLearnedRequest(
    val skill: String,
    val level: String,
)
