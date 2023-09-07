package com.ss.skillsync.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 8/30/2023.
 */

@Destination()
@Composable
fun SignupScreen(
    navigator: DestinationsNavigator
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Blue),
        contentAlignment = Alignment.Center) {
        Text(text = "Hello From Signup", color = Color.White)
    }
}