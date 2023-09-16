package com.ss.skillsync.model

data class Skill(
    val id: String = "",
    val name: String = "",
    val level: SkillLevel = SkillLevel.NOCHOICE,
    val skillId: String = "",
)

enum class SkillLevel(val text: String) {
    BEGINNER("Beginner"),
    INTERMEDIATE("Intermediate"),
    ADVANCED("Advanced"),
    NOCHOICE("Rate Skill Level"),
}
