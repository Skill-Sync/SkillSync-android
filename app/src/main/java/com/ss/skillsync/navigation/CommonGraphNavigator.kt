package com.ss.skillsync.navigation

import androidx.navigation.NavController
import com.ramcosta.composedestinations.navigation.navigate
import com.ss.skillsync.editprofile.EditProfileNavigator
import com.ss.skillsync.editprofile.destinations.EditProfileScreenDestination
import com.ss.skillsync.home.HomeNavigator
import com.ss.skillsync.home.destinations.HomeScreenDestination
import com.ss.skillsync.onboarding.OnboardingNavigator
import com.ss.skillsync.onboarding.destinations.OnboardingScreenDestination
import com.ss.skillsync.profile.mentor.destinations.MentorProfileScreenDestination
import com.ss.skillsync.profile.user.destinations.UserProfileScreenDestination
import com.ss.skillsync.session.making.destinations.SessionMakingScreenDestination
import com.ss.skillsync.settings.SettingsNavigator
import com.ss.skillsync.settings.destinations.PrivacyPolicyScreenDestination
import com.ss.skillsync.settings.destinations.SettingsScreenDestination
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
    HomeNavigator,
    SettingsNavigator,
    EditProfileNavigator {

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
        navController.navigate(OnboardingScreenDestination((false)))
    }

    fun navigate(route: String) {
        navController.navigate(route)
    }

    override fun navigateToOnboarding(fromEditProfile: Boolean) {
        navController.navigate(OnboardingScreenDestination(fromEditProfile))
    }

    override fun popBackToEditProfile() {
        navController.popBackStack(EditProfileScreenDestination.route, false)
    }

    override fun navigateToProfile() {
        navController.navigate(UserProfileScreenDestination)
    }

    override fun navigateToSettings() {
        navController.navigate(SettingsScreenDestination)
    }

    override fun navigateToMentorProfile() {
        navController.navigate(MentorProfileScreenDestination)
    }

    override fun navigateToMatch() {
        navController.navigate(SessionMakingScreenDestination)
    }

    override fun navigateToSignInScreen() {
        navController.navigate(SignInScreenDestination)
    }

    override fun navigateToPrivacyPolicyScreen() {
        navController.navigate(PrivacyPolicyScreenDestination)
    }

    override fun navigateToEditProfileScreen() {
        navController.navigate(EditProfileScreenDestination)
    }

    override fun popBackStack() {
        navController.popBackStack()
    }
}
