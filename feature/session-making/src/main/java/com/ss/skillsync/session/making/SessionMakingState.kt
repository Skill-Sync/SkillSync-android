package com.ss.skillsync.session.making

import com.ss.skillsync.model.Skill
import com.ss.skillsync.model.User

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/14/2023.
 */
data class SessionMakingState(
    val searchQuery: String = "",
    val searchResult: List<Skill> = emptyList(),
    val selectedSkill: Skill? = null,
    val isSearching: Boolean = false,
    val matchedUser: User? = null,
) {
    val canStartSearching: Boolean
        get() = searchQuery.isNotBlank() && !isSearching

    val isMatchFound: Boolean
        get() = matchedUser != null

    val isSkillSelectionDisplayed: Boolean
        get() = !isMatchFound && !isSearching

    val isSearchingDisplayed: Boolean
        get() = isSearching && !isMatchFound && !isSkillSelectionDisplayed

    val isMatchFoundDisplayed: Boolean
        get() = isMatchFound && !isSearching && !isSearchingDisplayed
}
