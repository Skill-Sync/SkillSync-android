package com.ss.skillsync.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ss.skillsync.commonandroid.components.ScreenColumn

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 09/09/2023
 */

@Destination
@Composable
fun HomeScreen() {
    ScreenColumn {
        Text(text = "Hello In Home Screen")
    }
}