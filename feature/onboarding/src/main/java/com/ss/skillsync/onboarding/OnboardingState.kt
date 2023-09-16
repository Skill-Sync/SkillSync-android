package com.ss.skillsync.onboarding

import com.ss.skillsync.model.Skill
import com.ss.skillsync.model.SkillLevel

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 8/30/2023.
 */
data class OnboardingState(
    val currentPageIndex: Int = 0,
    val searchQuery: String = "",
    val queryResult: Set<Skill> = emptySet(),
    val selectedInterests: Set<Skill> = emptySet(),
    val selectedStrengths: Set<Skill> = emptySet(),
    val isUpdating: Boolean = false,
    val onboardingDone: Boolean = false,
    val fromEditProfile: Boolean = false,
    val error: Throwable? = null,
) {
    val isNextEnabled: Boolean
        get() = selectedInterests.isNotEmpty()
    val isDoneEnabled: Boolean
        get() = selectedStrengths.isNotEmpty()
            && selectedStrengths.none { it.level == SkillLevel.NOCHOICE }
    val isBackVisible: Boolean
        get() = currentPageIndex != 0 || fromEditProfile
}
