package com.ss.skillsync.navigation

import androidx.navigation.NavController
import com.ramcosta.composedestinations.navigation.navigate
import com.ss.skillsync.home.HomeNavigator
import com.ss.skillsync.home.destinations.HomeScreenDestination
import com.ss.skillsync.onboarding.OnboardingNavigator
import com.ss.skillsync.onboarding.destinations.OnboardingScreenDestination
import com.ss.skillsync.signin.SignInNavigator
import com.ss.skillsync.signin.destinations.SignInScreenDestination
import com.ss.skillsync.signup.SignupNavigator
import com.ss.skillsync.signup.destinations.SignupScreenDestination
import com.ss.skillsync.welcome.WelcomeNavigator

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/6/2023.
 */
class CommonGraphNavigator(
    private val navController: NavController,
) : WelcomeNavigator,
    SignInNavigator,
    SignupNavigator,
    OnboardingNavigator,
    HomeNavigator {

    override fun leaveWelcomeScreen() {
        navController.navigate(SignupScreenDestination)
    }

    override fun navigateToSignUp() {
        navController.navigate(SignupScreenDestination)
    }

    override fun navigateToLogin() {
        navController.navigate(SignInScreenDestination)
    }

    override fun navigateToHome() {
        navController.navigate(HomeScreenDestination)
    }

    override fun navigateToOnboarding() {
        navController.navigate(OnboardingScreenDestination)
    }

    fun navigate(route: String) {
        navController.navigate(route)
    }
}
