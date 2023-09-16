package com.ss.skillsync.domain

import com.ss.skillsync.domain.util.StringUtil
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/16/2023.
 */
class TestStringUtil {

    @Test
    fun `StringUtil isUrl returns true if given a valid url`() {
        val validUrls = listOf(
            "google.com",
            "http://google.com",
            "https://google.com/path?=id"
        )

        assertTrue(StringUtil.isUrl(validUrls[0]))
        assertTrue(StringUtil.isUrl(validUrls[1]))
        assertTrue(StringUtil.isUrl(validUrls[2]))
    }

    @Test
    fun `StringUtil isUrl returns false if given an invalid url`() {
        val invalidUrls = listOf(
            "google",
            "google.",
            "google.com.",
        )

        assertTrue(invalidUrls.none { StringUtil.isUrl(it) })
    }

    @Test
    fun `StringUtil getRandomUrl returns valid Url`() {
        val url = StringUtil.getRandomImageUrl()

        assertTrue(StringUtil.isUrl(url))
    }

    @Test
    fun `StringUtil reformatHourTo12System returns valid time`() {
        val invalidTimes = listOf(
            "00:00 PM",
            "0:000 AM",
            "00:00 AM",
            "00:00 PM",
        )

        invalidTimes.forEach { time ->
            val formattedTime = StringUtil.reformatHourTo12System(time)
            assertTrue(formattedTime.startsWith("12:00"))
        }
    }
}