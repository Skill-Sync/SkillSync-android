package com.ss.skillsync.signin

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ss.skillsync.navigation.Graph
import com.ss.skillsync.navigation.Route

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 8/30/2023.
 */

@Graph.Auth(start = true)
@Destination(route = Route.signIn, start = true)
@Composable
fun SignInScreen(
    navigator: DestinationsNavigator
) {

}