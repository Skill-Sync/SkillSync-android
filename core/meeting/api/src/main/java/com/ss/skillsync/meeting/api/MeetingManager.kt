package com.ss.skillsync.meeting.api

import kotlinx.coroutines.flow.Flow

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/9/2023.
 */
interface MeetingManager {

    val messagesFlow: Flow<String?>
    fun joinMeeting(meetingName: String)
    fun leaveMeeting()
}