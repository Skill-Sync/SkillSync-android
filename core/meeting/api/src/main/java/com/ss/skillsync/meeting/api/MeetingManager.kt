package com.ss.skillsync.meeting.api

import kotlinx.coroutines.flow.Flow

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/9/2023.
 */
interface MeetingManager {

    val eventsFlow: Flow<MeetingEvent?>
    fun joinMeeting(meetingName: String)
    fun getMeetingDuration(): Long
    fun leaveMeeting()
}