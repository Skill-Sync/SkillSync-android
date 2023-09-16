package com.ss.skillsync.session.making

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/14/2023.
 */
sealed class SessionMakingDestinations {
    data object SkillSelection : SessionMakingDestinations()
    data object MatchSearching : SessionMakingDestinations()
    data object MatchFoundScreen : SessionMakingDestinations()

    data object AfterSessionScreen : SessionMakingDestinations()
}
