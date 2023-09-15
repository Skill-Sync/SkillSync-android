package com.ss.skillsync.home

import com.ss.skillsync.commonandroid.BaseNavigator
import com.ss.skillsync.model.Session

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 09/09/2023
 */
interface HomeNavigator: BaseNavigator {
    fun navigateToProfile()
    fun navigateToSettings()
    fun navigateToMentorProfile()
    fun navigateToSessionDetails(session: Session)
    fun navigateToMatch()
}