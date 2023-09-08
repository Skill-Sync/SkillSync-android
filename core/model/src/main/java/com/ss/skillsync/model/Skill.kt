package com.ss.skillsync.model

data class Skill(
    val id: Long = -1,
    val name: String = "",
    val level: SkillLevel = SkillLevel.INAPPLICABLE,
)

enum class SkillLevel {
    BEGINNER, INTERMEDIATE, ADVANCED, INAPPLICABLE
}
