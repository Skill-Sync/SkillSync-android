package com.ss.skillsync.navigation

import androidx.navigation.NavController
import com.ramcosta.composedestinations.navigation.navigate
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
    SignupNavigator {

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
    }

    override fun navigateToOnboarding() {
        // TODO change to onboarding screen
        navController.navigate(SignInScreenDestination)
    }
}