package com.ss.skillsync.data.source.remote.model.auth.signup

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/10/2023.
 */
data class SignupRequest(
    val name: String,
    val email: String,
    val pass: String,
    val passConfirm: String,
    val type: String = "user",
)
