package com.ss.skillsync.signin

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 8/30/2023.
 */
data class SignInState(
    val isSignupSuccess: Boolean = false,
    val isSignupFailed: Boolean = false,
    val isSignupLoading: Boolean = false
)
