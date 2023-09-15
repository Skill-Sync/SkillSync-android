package com.ss.skillsync.meeting.impl

import android.content.Context
import android.content.Intent

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/15/2023.
 */
class MeetingActionsManager(private val context: Context) {

    fun leaveMeeting() {
        val intent = Intent("org.jitsi.meet.HANG_UP")
    }
}