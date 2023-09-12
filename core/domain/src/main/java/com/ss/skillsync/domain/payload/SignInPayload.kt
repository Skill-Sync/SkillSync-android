package com.ss.skillsync.domain.payload

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 08/09/2023
 */
data class SignInPayload(
    val email: String,
    val password: String,
    val type: String,
)
