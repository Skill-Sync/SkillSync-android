package com.ss.skillsync.model

import java.util.Calendar

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
    val dateCalendar: Calendar,
    val status: SessionStatus = SessionStatus.NOT_SELECTED,
) {
    val isSkillAvailable: Boolean
        get() = skill.isNotEmpty()
}
