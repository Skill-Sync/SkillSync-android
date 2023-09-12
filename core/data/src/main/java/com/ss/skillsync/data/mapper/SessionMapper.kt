package com.ss.skillsync.data.mapper

import com.ss.skillsync.data.source.remote.model.session.SessionData
import com.ss.skillsync.domain.util.StringUtil
import com.ss.skillsync.model.Session
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/11/2023.
 */

fun SessionData.toDomain(): Session {
    val (date, time) = getDateAndTime(scheduledDate ?: "2023-11-10T11:00:00.000Z")
    val image = if (StringUtil.isUrl(photo)) {
        photo!!
    } else {
        StringUtil.getRandomImageUrl()
    }
    return Session(
        sessionId ?: "",
        name ?: "",
        image,
        skill ?: "",
        scheduledDate = date,
        scheduledHour = time,
    )
}

private fun getDateAndTime(scheduledDate: String): Pair<String, String> {
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    val date = sdf.parse(scheduledDate)
    val calendar = Calendar.getInstance()
    calendar.time = date!!
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    val month = calendar.get(Calendar.MONTH)
    val year = calendar.get(Calendar.YEAR)
    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    val minute = calendar.get(Calendar.MINUTE)

    val time = "$hour:$minute".padEnd(5, '0')
    return "$day/$month/$year" to time
}
