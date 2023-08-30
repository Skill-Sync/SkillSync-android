package com.ss.skillsync.presentation.navigation

import com.ramcosta.composedestinations.annotation.NavGraph

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 8/30/2023.
 */
sealed class Graph {
    @NavGraph
    annotation class Auth(val start: Boolean = false)

    class Route {
        companion object {
            const val signup = "sign_up"
            const val signIn = "sign_in"
        }
    }
}
