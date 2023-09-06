package com.ss.skillsync.navigation

import androidx.navigation.NavController
import com.ramcosta.composedestinations.navigation.navigate
import com.ss.skillsync.signin.SignInNavigator
import com.ss.skillsync.signup.destinations.SignupScreenDestination

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/6/2023.
 */
class CommonGraphNavigator(
    private val navController: NavController,
) : SignInNavigator {
    override fun navigateToSignUp() {
        navController.navigate(SignupScreenDestination)
    }

    override fun navigateToHome() {

    }
}