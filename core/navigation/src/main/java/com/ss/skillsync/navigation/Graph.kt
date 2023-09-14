package com.ss.skillsync.navigation

import com.ramcosta.composedestinations.annotation.NavGraph
import com.ramcosta.composedestinations.annotation.RootNavGraph

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 8/30/2023.
 */
sealed class Graph {
    @RootNavGraph(start = true)
    @NavGraph
    annotation class Auth(val start: Boolean = false)
}
