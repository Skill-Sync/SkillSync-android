package com.ss.skillsync.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.DependenciesContainerBuilder
import com.ramcosta.composedestinations.navigation.dependency
import com.ss.skillsync.model.NavigationParams

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/6/2023.
 */
private fun DependenciesContainerBuilder<*>.currentNavigator(): CommonGraphNavigator {
    return CommonGraphNavigator(
        navController,
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun AppNavigation(
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState,
    navigationParams: NavigationParams,
) {
    val navGraph = NavGraphs.create(navigationParams).root
    val navController = rememberAnimatedNavController()

    DestinationsNavHost(
        modifier = modifier,
        navGraph = navGraph,
        navController = navController,
        dependenciesContainerBuilder = {
            dependency(currentNavigator())
            dependency(snackbarHostState)
        },
    )
}
