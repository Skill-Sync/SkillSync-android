package com.ss.skillsync.navigation

import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.ramcosta.composedestinations.spec.Route
import com.ss.skillsync.R
import com.ss.skillsync.editprofile.destinations.EditProfileScreenDestination
import com.ss.skillsync.friends.destinations.FriendListScreenDestination
import com.ss.skillsync.home.destinations.HomeScreenDestination
import com.ss.skillsync.model.NavigationParams
import com.ss.skillsync.onboarding.destinations.OnboardingScreenDestination
import com.ss.skillsync.profile.mentor.destinations.MentorProfileScreenDestination
import com.ss.skillsync.profile.user.destinations.UserProfileScreenDestination
import com.ss.skillsync.session.making.destinations.SessionMakingScreenDestination
import com.ss.skillsync.settings.destinations.PrivacyPolicyScreenDestination
import com.ss.skillsync.settings.destinations.SettingsScreenDestination
import com.ss.skillsync.signin.destinations.SignInScreenDestination
import com.ss.skillsync.signup.destinations.SignupScreenDestination
import com.ss.skillsync.welcome.destinations.WelcomeScreenDestination
import com.ss.skillsync.commonandroid.R as commonRes

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/7/2023.
 */
class NavGraphs private constructor(
    private var navigationParams: NavigationParams,
) {
    val root = object : NavGraphSpec {
        override val destinationsByRoute: Map<String, DestinationSpec<*>>
            get() = emptyMap()
        override val route: String
            get() = "root"
        override val startRoute: Route
            get() = getRootStartRoute()

        override val nestedNavGraphs: List<NavGraphSpec>
            get() = listOf(
                welcome,
                auth,
                onboarding,
                main,
                onboarding,
                settings,
            )
    }

    val auth = object : NavGraphSpec {
        override val destinationsByRoute: Map<String, DestinationSpec<*>>
            get() = mapOf(
                SignupScreenDestination.route to SignupScreenDestination,
                SignInScreenDestination.route to SignInScreenDestination,
            )

        override val route: String
            get() = "auth"
        override val startRoute: Route
            get() = SignInScreenDestination
    }

    val onboarding = object : NavGraphSpec {
        override val destinationsByRoute: Map<String, DestinationSpec<*>>
            get() = mapOf(
                OnboardingScreenDestination.route to OnboardingScreenDestination,
            )

        override val route: String
            get() = "onboarding"
        override val startRoute: Route
            get() = OnboardingScreenDestination
    }

    val main = object : NavGraphSpec {
        override val destinationsByRoute: Map<String, DestinationSpec<*>>
            get() = mapOf(
                HomeScreenDestination.route to HomeScreenDestination,
                SessionMakingScreenDestination.route to SessionMakingScreenDestination,
                FriendListScreenDestination.route to FriendListScreenDestination,
                MentorProfileScreenDestination.route to MentorProfileScreenDestination,
                UserProfileScreenDestination.route to UserProfileScreenDestination,
            )
        override val route: String
            get() = "main"
        override val startRoute: Route
            get() = HomeScreenDestination
    }

    val welcome = object : NavGraphSpec {
        override val destinationsByRoute: Map<String, DestinationSpec<*>>
            get() = mapOf(
                WelcomeScreenDestination.route to WelcomeScreenDestination,
            )
        override val route: String
            get() = "welcome"
        override val startRoute: Route
            get() = WelcomeScreenDestination
    }

    val settings = object : NavGraphSpec {
        override val destinationsByRoute: Map<String, DestinationSpec<*>>
            get() = mapOf(
                SettingsScreenDestination.route to SettingsScreenDestination,
                EditProfileScreenDestination.route to EditProfileScreenDestination,
                PrivacyPolicyScreenDestination.route to PrivacyPolicyScreenDestination,
            )
        override val route: String
            get() = "settings"
        override val startRoute: Route
            get() = SettingsScreenDestination
    }

    fun getBottomNavRoutes(): List<Pair<Route, Int>> {
        return listOf(
            HomeScreenDestination to R.drawable.ic_home,
            SessionMakingScreenDestination to commonRes.drawable.ic_matching,
            FriendListScreenDestination to R.drawable.ic_friends,
//            SettingsScreenDestination to -1,
        )
    }

    private fun getRootStartRoute(): Route {
        val isFirstOpen = navigationParams.isFirstTime
        val isUserActive = navigationParams.isUserActive
        val isUserOnboardingDone = navigationParams.isOnboardingComplete
        return if (isFirstOpen) {
            welcome
        } else {
            if (isUserActive) {
                if (isUserOnboardingDone) {
                    main
                } else {
                    onboarding
                }
            } else {
                auth
            }
        }
    }

    companion object {
        private var instance: NavGraphs? = null
        fun create(navigationParams: NavigationParams): NavGraphs {
            return instance?.also { it.navigationParams = navigationParams } ?: NavGraphs(
                navigationParams,
            ).also {
                instance = it
            }
        }

        fun get(): NavGraphs {
            return instance ?: throw IllegalStateException("NavGraphs is not initialized")
        }
    }
}
