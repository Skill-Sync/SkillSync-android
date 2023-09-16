package com.ss.skillsync.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.defaults.RootNavGraphDefaultAnimations
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import com.ramcosta.composedestinations.navigation.DependenciesContainerBuilder
import com.ramcosta.composedestinations.navigation.dependency

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/6/2023.
 */
private fun DependenciesContainerBuilder<*>.currentNavigator(): CommonGraphNavigator {
    return CommonGraphNavigator(
        navController,
    )
}

@Composable
internal fun AppNavigation(
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState,
    navController: NavHostController,
    navGraph: NavGraphs = NavGraphs.get(),
) {
    DestinationsNavHost(
        modifier = modifier,
        navGraph = navGraph.root,
        navController = navController,
        dependenciesContainerBuilder = {
            dependency(currentNavigator())
            dependency(snackbarHostState)
        },
    )
}
