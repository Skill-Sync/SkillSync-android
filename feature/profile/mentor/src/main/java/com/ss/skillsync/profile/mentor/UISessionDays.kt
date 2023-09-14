package com.ss.skillsync.profile.mentor

import com.ss.skillsync.domain.util.getDayOfMonth
import com.ss.skillsync.domain.util.getMonthDisplayName
import com.ss.skillsync.domain.util.getYear
import com.ss.skillsync.domain.util.inTheSameMonthAndYear
import com.ss.skillsync.model.Day
import com.ss.skillsync.model.SessionDays

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/12/2023.
 */
data class UISessionDays(
    val start: String,
    val end: String,
    val days: List<Day>
) {
    companion object {
        fun from(days: SessionDays): UISessionDays {
            return with(days) {
                val start = if (start.inTheSameMonthAndYear(end)) {
                    start.getDayOfMonth().toString()
                } else {
                    "${start.getDayOfMonth()} ${start.getMonthDisplayName()}"
                }

                val end = "${end.getDayOfMonth()} ${end.getMonthDisplayName()} ${end.getYear()}"

                UISessionDays(
                    start = start,
                    end = end,
                    days = days.days
                )
            }
        }
    }
}

