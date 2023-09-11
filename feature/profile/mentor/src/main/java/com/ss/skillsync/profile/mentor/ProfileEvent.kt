package com.ss.skillsync.profile.mentor

import com.ss.skillsync.model.Session

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/11/2023.
 */
sealed class ProfileEvent {
    data class OnSessionClicked(val session: Session) : ProfileEvent()
}