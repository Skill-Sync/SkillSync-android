package com.ss.skillsync.signin

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 8/30/2023.
 */
data class SignInState(
    val email: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSignInSuccessful: Boolean = false,
    val isFirstOpen: Boolean = false,
    val isOnboardingCompleted: Boolean = false,
    val loginType: String = "user"
) {
    fun isSignInFailed() = error != null && isSignInSuccessful.not()

    fun navigate(
        toHomeScreen: () -> Unit = {},
        toOnboarding: () -> Unit = {},
    ) {
        if (isSignInSuccessful.not()) return
        if (isOnboardingCompleted.not()) {
            toOnboarding()
        } else {
            toHomeScreen()
        }
    }
}
