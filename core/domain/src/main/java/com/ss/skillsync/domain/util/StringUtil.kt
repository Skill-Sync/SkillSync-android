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
        val regex =
            "^(https?://)?[A-Za-z0-9.-]+(/[A-Za-z0-9%.-]*)*(\\?[A-Za-z0-9%.-]*)?$"
        return url?.contains(Regex(regex)) ?: false
    }

    fun reformatHourTo12System(hour: String): String {
        val parts = hour.split(" ")
        if (parts.size == 2) {
            val timePart = parts[0]
            val amPmPart = parts[1]

            val timeComponents = timePart.split(":")
            if (timeComponents.size == 2) {
                val hourValue = timeComponents[0].toIntOrNull() ?: 0
                val minuteValue = timeComponents[1].toIntOrNull() ?: 0

                val formattedHour = when {
                    hourValue == 0 -> "12"
                    hourValue < 10 -> "0$hourValue"
                    else -> hourValue.toString()
                }

                val formattedMinute = when {
                    minuteValue < 10 -> "0$minuteValue"
                    else -> minuteValue.toString()
                }

                return "$formattedHour:$formattedMinute $amPmPart"
            }
        }

        // Return the input unchanged if it doesn't match the expected format
        return hour
    }
}
