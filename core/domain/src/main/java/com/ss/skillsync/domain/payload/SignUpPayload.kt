package com.ss.skillsync.domain.payload

import com.ss.skillsync.model.Skill

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 08/09/2023
 */

data class SignUpPayload(
    val name: String,
    val email: String,
    val password: String,
    val passwordConf: String,
    val interestedSkills: List<Skill>,
    val strengths: List<Skill>,
)