package com.ss.skillsync.navigation

import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.ramcosta.composedestinations.spec.Route
import com.ss.skillsync.model.NavigationParams
import com.ss.skillsync.signin.destinations.SignInScreenDestination
import com.ss.skillsync.signup.destinations.SignupScreenDestination
import com.ss.skillsync.welcome.destinations.WelcomeScreenDestination

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
                auth
            )
    }

    val auth = object : NavGraphSpec {
        override val destinationsByRoute: Map<String, DestinationSpec<*>>
            get() = mapOf(
                SignupScreenDestination.route to SignupScreenDestination,
                SignInScreenDestination.route to SignInScreenDestination
            )

        override val route: String
            get() = "auth"
        override val startRoute: Route
            get() = SignInScreenDestination
    }

    val main = object : NavGraphSpec {
        override val destinationsByRoute: Map<String, DestinationSpec<*>>
            get() = emptyMap()
        override val route: String
            get() = "main"
        override val startRoute: Route
            get() = WelcomeScreenDestination
    }

    val welcome = object : NavGraphSpec {
        override val destinationsByRoute: Map<String, DestinationSpec<*>>
            get() = mapOf(
                WelcomeScreenDestination.route to WelcomeScreenDestination
            )
        override val route: String
            get() = "welcome"
        override val startRoute: Route
            get() = WelcomeScreenDestination
    }

    private fun getRootStartRoute(): Route {
        val isFirstOpen = navigationParams.isFirstTime
        val isUserActive = navigationParams.isUserActive
        return if (isFirstOpen) {
            welcome
        } else {
            if (isUserActive) {
                main
            } else {
                auth
            }
        }
    }

    companion object {
        private var instance: NavGraphs? = null
        fun create(navigationParams: NavigationParams): NavGraphs {
            return instance?.also { it.navigationParams = navigationParams } ?: NavGraphs(
                navigationParams
            ).also {
                instance = it
            }
        }

        fun get(): NavGraphs {
            return instance ?: throw IllegalStateException("NavGraphs is not initialized")
        }
    }
}