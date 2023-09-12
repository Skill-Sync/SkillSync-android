package com.ss.skillsync.domain.util

import java.util.Calendar
import java.util.Locale

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/12/2023.
 */

fun Calendar.getDayDisplayName(): String {
    return getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault()) ?:
    get(Calendar.DAY_OF_WEEK).toString()
}

fun Calendar.getMonthDisplayName(): String {
    return getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault()) ?:
    get(Calendar.MONTH).toString()
}

fun Calendar.getMonth(): Int {
    return get(Calendar.MONTH)
}

fun Calendar.getYear(): Int {
    return get(Calendar.YEAR)
}

fun Calendar.inTheSameMonthAndYear(calendar: Calendar): Boolean {
    return getMonth() == calendar.getMonth() && getYear() == calendar.getYear()
}
fun Calendar.getDayOfMonth(): Int {
    return get(Calendar.DAY_OF_MONTH)
}

fun Calendar.getHourOfDay(): Int {
    return get(Calendar.HOUR_OF_DAY)
}