package com.ss.skillsync.data.source.remote.model.auth.signin

data class SignInRequest(
    val email: String,
    val pass: String,
    val type: String,
)
