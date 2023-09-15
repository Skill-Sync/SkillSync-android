package com.ss.skillsync.model

import java.util.Calendar

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/12/2023.
 */
data class SessionDays(
    val start: Calendar,
    val end: Calendar,
    val days: List<Day>
)