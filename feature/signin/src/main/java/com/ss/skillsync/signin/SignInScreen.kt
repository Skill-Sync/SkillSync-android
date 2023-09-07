package com.ss.skillsync.signin

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 8/30/2023.
 */

interface SignInNavigator {
    fun navigateToSignUp()
    fun navigateToHome()
}

@Destination
@Composable
fun SignInScreen(
    navigator: SignInNavigator,
) {

}