package com.ss.skillsync.domain.util

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/11/2023.
 */
object StringUtil {
    fun getRandomImageUrl(): String {
        val random = (1..100).random()
        return "https://picsum.photos/seed/$random/200/300"
    }

    fun isUrl(url: String?): Boolean {
        return url?.startsWith("http") ?: false
    }

    fun reformatHourTo12System(hour: String): String {
        return if (hour.none { it.isDigit() && it.digitToInt() > 0 }) {
            "12${hour.substring(1)}".padEnd(4, '0')
        } else {
            val timeSplit = hour.split(" ")
            val amPm = timeSplit[1]
            val time = timeSplit[0].split(":")
            val hour = time[0].padStart(2, '0')
            val minute = time[1].padStart(2, '0')
            "$hour:$minute $amPm"
        }
    }
}
