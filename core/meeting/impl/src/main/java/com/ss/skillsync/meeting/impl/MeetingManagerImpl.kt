package com.ss.skillsync.meeting.impl

import android.content.Context
import androidx.activity.ComponentActivity
import com.ss.skillsync.meeting.api.MeetingEvent
import com.ss.skillsync.meeting.api.MeetingManager
import dagger.hilt.android.qualifiers.ActivityContext
import dyte.io.uikit.DyteUIKit
import dyte.io.uikit.DyteUIKitBuilder
import dyte.io.uikit.DyteUIKitInfo
import io.dyte.core.models.DyteMeetingInfoV2
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/9/2023.
 */
class MeetingManagerImpl @Inject constructor(
    @ActivityContext private val context: Context,
) : MeetingManager {
    private val eventListener = MeetingEventListener()
    override val eventsFlow: Flow<MeetingEvent?> = eventListener.events

    private var meetingStartTime: Long = 0L
    private var meetingEndTime: Long = 0L

    override fun joinMeeting(meetingName: String) {
        val meetingInfo = DyteMeetingInfoV2(
            authToken = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJvcmdJZCI6ImNhOWJiNzczLWZhYTMtNDk4My1hN2E0LWQwY2RkZDA3ODE4ZCIsIm1lZXRpbmdJZCI6ImJiYjQ1OGI0LTRiYzItNGVmZS05YzUyLWFiMzY2ZDY0OWVhMSIsInBhcnRpY2lwYW50SWQiOiJhYWFlNDNkNi02Y2M3LTQ1MmUtYmQ0Yy0zOWI0MzM2ZmRmZWMiLCJwcmVzZXRJZCI6ImM2NGFjMWIwLWI2ZjctNGU5MS1hOTRlLTFlOGYxZWQ2ZGQ0NyIsImlhdCI6MTY5NDc2NzU3NiwiZXhwIjoxNzAzNDA3NTc2fQ.TnUUPdHg1HxRwluZFuzr3Ao6Oe6yCeLPcvu61RR19SOYGQDEqZVPIrg0xyKjYISbbqDsK9GpQ53Jhmw-aIcMNTXfqg4AeqVcGB3UNYJ7RhTGc2yYkCXt11S6px0SWA_QANX1mgbWUmmrCusKS9StAJMoi2DheMhe1AhvYtgIUoiO9fy9-c303XBTrWai6ZSG8CP6pZIxpUcCiJDbxe66UFb76n5Wxdv1HFtg4B5G4blWS4GSN3AKSSUECJq-Uh-QvZmoJl0u_ddmF_v7998uF-8V0up43MVs7DWMjlfGUg-XFOeOQXk97kdkJDQoQHde0ty5qvQLQuKa4Z2FjEjOUw"
        )
        initSDK(meetingInfo).apply {
            startMeeting()
            meeting.addMeetingRoomEventsListener(eventListener)
        }
    }

    override fun getMeetingDuration(): Long {
        TODO("Not yet implemented")
    }

    override fun leaveMeeting() {
        TODO("Not yet implemented")
    }

    private fun initSDK(meetingInfoV2: DyteMeetingInfoV2): DyteUIKit {
        val dyteUIKitInfo = DyteUIKitInfo(
            activity = context as ComponentActivity,
            dyteMeetingInfo = meetingInfoV2,
        )
        return DyteUIKitBuilder.build(dyteUIKitInfo)
    }
}