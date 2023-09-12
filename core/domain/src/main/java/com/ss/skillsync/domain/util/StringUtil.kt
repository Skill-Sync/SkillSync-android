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
}
