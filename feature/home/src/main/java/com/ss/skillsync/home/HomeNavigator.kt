package com.ss.skillsync.home

import com.ss.skillsync.model.Mentor
import com.ss.skillsync.model.Session

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 09/09/2023
 */
interface HomeNavigator {
    fun navigateToProfile()
    fun navigateToSettings()
    fun navigateToMentorProfile(mentor: Mentor)
    fun navigateToSessionDetails(session: Session)
    fun navigateToMatch()
}