package com.ss.skillsync.meeting.impl

import com.ss.skillsync.meeting.api.MeetingEvent
import io.dyte.core.listeners.DyteMeetingRoomEventsListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/15/2023.
 */
class MeetingEventListener : DyteMeetingRoomEventsListener {

    private val _events = MutableStateFlow(MeetingEvent.IDLE)
    val events = _events.asStateFlow()

    override fun onMeetingRoomLeaveCompleted() {
        super.onMeetingRoomLeaveCompleted()
        _events.value = MeetingEvent.USER_DISCONNECTED
    }

    override fun onMeetingRoomDisconnected() {
        super.onMeetingRoomDisconnected()
        _events.value = MeetingEvent.MEETING_ENDED
    }

}