package com.ss.skillsync.commonandroid

import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import com.ss.skillsync.commonandroid.theme.Black

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/7/2023.
 */
@Composable
fun DefaultSnackbarHost(state: SnackbarHostState) {
    SnackbarHost(state) {
        Snackbar(
            snackbarData = it,
            containerColor = Black,
        )
    }
}
