package com.ss.skillsync.commonandroid.composition

import androidx.compose.runtime.compositionLocalOf
import com.ss.skillsync.meeting.api.MeetingManager

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/9/2023.
 */
val LocalMeetingManager = compositionLocalOf<MeetingManager> {
    error("No MeetingManager provided")
}