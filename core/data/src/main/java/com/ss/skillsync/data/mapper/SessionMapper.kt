package com.ss.skillsync.data.mapper

import com.ss.skillsync.data.source.remote.model.session.SessionData
import com.ss.skillsync.domain.util.StringUtil
import com.ss.skillsync.model.Session
import com.ss.skillsync.model.SessionStatus
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/11/2023.
 */

fun SessionData.toDomain(): Session {
    val (date, time, calendar) = getDateAndTime(scheduledDate ?: "2023-11-10T11:00:00.000Z")
    val image = if (StringUtil.isUrl(photo)) {
        photo!!
    } else {
        StringUtil.getRandomImageUrl()
    }
    val status = when (status) {
        "accepted" -> SessionStatus.ACCEPTED
        "pending" -> SessionStatus.PENDING
        else -> SessionStatus.NOT_SELECTED
    }
    return Session(
        sessionId = sessionId ?: "",
        name = name ?: "",
        image = image,
        skill = skill ?: "",
        status = status,
        scheduledDate = date,
        scheduledHour = time,
        dateCalendar = calendar,
    )
}

private fun getDateAndTime(scheduledDate: String): DateAndTime {
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    val date = sdf.parse(scheduledDate)
    val calendar = Calendar.getInstance()
    calendar.time = date!!
    val hour = calendar.get(Calendar.HOUR)
    val minute = calendar.get(Calendar.MINUTE)
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    val month = calendar.get(Calendar.MONTH) + 1
    val year = calendar.get(Calendar.YEAR)
    val isPM = calendar.get(Calendar.AM_PM) == Calendar.PM

    var timeString = "$hour:$minute".padEnd(4, '0')
    timeString += if (isPM) {
        " PM"
    } else {
        " AM"
    }
    timeString = StringUtil.reformatHourTo12System(timeString)
    val dateString = "$day/$month/$year"
    return DateAndTime(dateString, timeString, calendar)
}

private data class DateAndTime(
    val date: String,
    val time: String,
    val calendar: Calendar
)
