package com.ss.skillsync.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.DependenciesContainerBuilder
import com.ramcosta.composedestinations.navigation.dependency
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.ramcosta.composedestinations.spec.Route
import com.ss.skillsync.signin.destinations.SignInScreenDestination
import com.ss.skillsync.signup.destinations.SignupScreenDestination

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/6/2023.
 */
object NavGraphs {
    val root = object : NavGraphSpec {
        override val destinationsByRoute: Map<String, DestinationSpec<*>>
            get() = emptyMap()
        override val route: String
            get() = "root"
        override val startRoute: Route
            get() = auth

        override val nestedNavGraphs: List<NavGraphSpec>
            get() = listOf(
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


    private fun DependenciesContainerBuilder<*>.currentNavigator(): CommonGraphNavigator {
        return CommonGraphNavigator(
            navController,
        )
    }

    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    internal fun AppNavigation(
        modifier: Modifier = Modifier,
    ) {
        val navController = rememberAnimatedNavController()


        DestinationsNavHost(
            modifier = modifier,
            navGraph = root,
            navController = navController,
            dependenciesContainerBuilder = {
                dependency(currentNavigator())
            }
        )
    }
}