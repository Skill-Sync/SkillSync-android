package com.ss.skillsync.signup

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ss.skillsync.navigation.Graph
import com.ss.skillsync.navigation.Route

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 8/30/2023.
 */

@Graph.Auth
@Destination(route = Route.signup)
@Composable
fun SignupScreen(
    navigator: DestinationsNavigator
) {

}