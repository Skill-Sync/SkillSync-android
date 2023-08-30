package com.ss.skillsync.presentation.authentication.signin

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ss.skillsync.presentation.navigation.Graph

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 8/30/2023.
 */

@Graph.Auth(start = true)
@Destination(route = Graph.Route.signIn)
@Composable
fun SignInScreen(
    navigator: DestinationsNavigator
) {

}