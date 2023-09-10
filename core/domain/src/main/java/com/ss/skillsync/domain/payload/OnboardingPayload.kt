package com.ss.skillsync.domain.payload

import com.ss.skillsync.model.Skill

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/8/2023.
 */
data class OnboardingPayload(
    val interestedSkills: List<Skill>,
    val strengths: List<Skill>,
)
