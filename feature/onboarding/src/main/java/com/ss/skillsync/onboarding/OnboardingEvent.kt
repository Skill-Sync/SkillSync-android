package com.ss.skillsync.onboarding

import com.ss.skillsync.model.Skill
import com.ss.skillsync.model.SkillLevel

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 09/09/2023
 */
sealed class OnboardingEvent {
    data class SearchQueryChanged(val query: String) : OnboardingEvent()
    data class SkillSelected(val skill: Skill) : OnboardingEvent()
    data class SkillRemoved(val skill: Skill) : OnboardingEvent()
    data class SkillLevelUpdated(val skill: Skill, val skillLevel: SkillLevel) : OnboardingEvent()
    object NextClicked : OnboardingEvent()
    object BackClicked : OnboardingEvent()
    object DoneClicked : OnboardingEvent()
}