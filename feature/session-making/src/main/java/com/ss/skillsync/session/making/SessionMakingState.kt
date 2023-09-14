package com.ss.skillsync.session.making

import com.ss.skillsync.model.MatchResult
import com.ss.skillsync.model.Skill

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/14/2023.
 */
data class SessionMakingState(
    val searchQuery: String = "",
    val searchResult: List<Skill> = emptyList(),
    val selectedSkill: Skill? = null,
    val isSearching: Boolean = false,
    val userProfileImage: String? = null,
    val matchResult: MatchResult? = null
) {
    val canStartSearching: Boolean
        get() = searchQuery.isNotBlank() && !isSearching

    val isMatchFound: Boolean
        get() = matchResult != null

    private val isSkillSelectionDisplayed: Boolean
        get() = !isMatchFound && !isSearching

    private val isSearchingDisplayed: Boolean
        get() = isSearching && !isMatchFound && !isSkillSelectionDisplayed

    private val isMatchFoundDisplayed: Boolean
        get() = isMatchFound && !isSearching && !isSearchingDisplayed

    val currentDestination: SessionMakingDestinations
        get() = when {
            isSkillSelectionDisplayed -> SessionMakingDestinations.SkillSelection
            isSearchingDisplayed -> SessionMakingDestinations.MatchSearching
            isMatchFoundDisplayed -> SessionMakingDestinations.MatchFoundScreen
            else -> throw IllegalStateException("Invalid state")
        }
}


