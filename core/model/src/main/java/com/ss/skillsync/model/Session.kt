package com.ss.skillsync.model

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/10/2023.
 */
data class Session(
    val sessionId: String = "",
    val name: String = "",
    val image: String = "",
    val skill: String = "",
    val scheduledHour: String = "",
    val scheduledDate: String = "",
) {
    val isSkillAvailable: Boolean
        get() = skill.isNotEmpty()
}
