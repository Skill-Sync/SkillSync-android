package com.ss.skillsync.meeting.impl

import android.app.Activity
import android.content.Context
import com.ss.skillsync.meeting.api.MeetingManager
import dagger.hilt.android.qualifiers.ActivityContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import org.jitsi.meet.sdk.JitsiMeet
import org.jitsi.meet.sdk.JitsiMeetActivity
import org.jitsi.meet.sdk.JitsiMeetActivityDelegate
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions
import java.net.URL
import javax.inject.Inject

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/9/2023.
 */
class MeetingManagerImpl @Inject constructor(
    @ActivityContext private val context: Context,
) : MeetingManager {

    private val _messagesFlow = MutableStateFlow<String?>(null)
    override val messagesFlow: Flow<String?> = _messagesFlow

    init {
        val defaultOptions = getMeetingOptions()
        JitsiMeet.setDefaultConferenceOptions(defaultOptions)
    }

    override fun joinMeeting(meetingName: String) {
        JitsiMeetConferenceOptions.Builder()
            .setRoom(meetingName)
            .build()
            .let {
                JitsiMeetActivity.launch(context, it)
            }

    }

    override fun leaveMeeting() {
        JitsiMeetActivityDelegate.onHostDestroy(context as? Activity)
    }

    private fun getMeetingOptions(): JitsiMeetConferenceOptions {
        return JitsiMeetConferenceOptions.Builder()
            .setServerURL(URL("https://meet.jit.si"))
            .setFeatureFlag("chat.enabled", true)
            .setFeatureFlag("filmstrip.enabled", false)
            .setFeatureFlag("android.screensharing.enabled", false)
            .setFeatureFlag("video-mute.enabled", true)
            .setFeatureFlag("invite.enabled", false)
            .setFeatureFlag("pip.enabled", true)
            .setFeatureFlag("welcomepage.enabled", false)
            .setFeatureFlag("add-people.enabled", false)
            .setFeatureFlag("calendar.enabled", false)
            .setFeatureFlag("call-integration.enabled", false)
            .setFeatureFlag("close-captions.enabled", false)
            .setFeatureFlag("conference-timer.enabled", true)
            .setFeatureFlag("help.enabled", false)
            .setFeatureFlag("kick-out.enabled", false)
            .setFeatureFlag("live-streaming.enabled", false)
            .setFeatureFlag("meeting-name.enabled", false)
            .setFeatureFlag("lobby-mode.enabled", false)
            .setFeatureFlag("meeting-password.enabled", false)
            .setFeatureFlag("raise-hand.enabled", false)
            .setFeatureFlag("prejoinpage.hideDisplayName", true)
            .setFeatureFlag("recording.enabled", false)
            .setFeatureFlag("security-options.enabled", false)
            .setFeatureFlag("settings.enabled", false)
            .setFeatureFlag("reactions.enabled", false)
            .setFeatureFlag("video-share.enabled", false)
            .build()
    }
}