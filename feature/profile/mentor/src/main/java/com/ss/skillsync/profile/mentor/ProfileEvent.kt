package com.ss.skillsync.profile.mentor

import com.ss.skillsync.model.Day
import com.ss.skillsync.model.Session

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/11/2023.
 */
sealed class ProfileEvent {
    data class OnDayClicked(val day: Day) : ProfileEvent()
    data class OnSessionClicked(val session: Session) : ProfileEvent()

    data object OnBookSessionClicked : ProfileEvent()
    data object OnSessionBookedSuccessfully : ProfileEvent()
    data object OnBackClicked : ProfileEvent()
}