package com.ss.skillsync.model

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/12/2023.
 */
data class Day(
    val day: String, // Sat
    val dayOfMonth: Int, // 12
    val startHour: String, // 10:00 AM
    val endHour: String, // 11:00 AM
    val sessions: List<Session>
)
