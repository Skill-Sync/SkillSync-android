package com.ss.skillsync.signin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ss.skillsync.commonandroid.components.BrandButton
import com.ss.skillsync.commonandroid.components.ClickableText
import com.ss.skillsync.commonandroid.components.HeaderLargeText
import com.ss.skillsync.commonandroid.components.RoundedPasswordTextField
import com.ss.skillsync.commonandroid.components.RoundedTextFieldWithTitle
import com.ss.skillsync.commonandroid.components.ScreenColumn
import com.ss.skillsync.commonandroid.theme.Blue
import com.ss.skillsync.commonandroid.theme.Purple

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 8/30/2023.
 */

interface SignInNavigator {
    fun navigateToSignUp()
    fun navigateToHome()
    fun navigateToOnboarding()
}

@Destination
@Composable
fun SignInScreen(
    navigator: SignInNavigator,
    snackbarHostState: SnackbarHostState,
    viewModel: SignInViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    if (state.isSignInFailed()) {
        val text = state.error ?: stringResource(R.string.something_went_wrong)
        LaunchedEffect(Unit) {
            snackbarHostState.showSnackbar(
                message = text,
                duration = SnackbarDuration.Short
            )
            viewModel.resetErrors()
        }
    }

    state.navigate(
        toHomeScreen = navigator::navigateToHome,
        toOnboarding = navigator::navigateToOnboarding
    )

    SignInContent(
        state = state,
        onEmailChanged = viewModel::onEmailChanged,
        onPasswordChanged = viewModel::onPasswordChanged,
        onSignInClicked = viewModel::onSignInClicked,
        onSignupClicked = {
            navigator.navigateToSignUp()
        }
    )

}

@Composable
private fun SignInContent(
    state: SignInState,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onSignInClicked: () -> Unit = {},
    onSignupClicked: () -> Unit = {},
) {
    ScreenColumn {
        HeaderSection()
        SignInForm(
            state = state,
            onEmailChanged = onEmailChanged,
            onPasswordChanged = onPasswordChanged,
        )
        SignInButtonsSection(
            onSignInClicked = onSignInClicked,
            onSignupClicked = onSignupClicked
        )
    }
}

@Composable
private fun HeaderSection(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        HeaderLargeText(text = stringResource(R.string.welcome_back))
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
private fun SignInForm(
    modifier: Modifier = Modifier,
    state: SignInState,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        val space = 15.dp
        RoundedTextFieldWithTitle(
            title = stringResource(R.string.email),
            value = state.email,
            onValueChange = onEmailChanged
        )
        Spacer(modifier = Modifier.height(space))
        RoundedPasswordTextField(
            title = stringResource(R.string.password),
            value = state.password,
            onValueChange = onPasswordChanged
        )
    }
}

@Composable
private fun SignInButtonsSection(
    modifier: Modifier = Modifier,
    onSignInClicked: () -> Unit,
    onSignupClicked: () -> Unit
) {
    Column(modifier = modifier.fillMaxWidth()) {
        BrandButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.login),
            onClick = onSignInClicked,
            background = Brush.horizontalGradient(
                listOf(
                    Purple,
                    Blue
                )
            ),
            textColor = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(10.dp))
        FooterSection(
            onSignupClicked = onSignupClicked
        )
    }
}

@Composable
private fun FooterSection(
    modifier: Modifier = Modifier,
    onSignupClicked: () -> Unit,
) {
    Row(
        modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.you_dont_have_an_account),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.width(5.dp))
        ClickableText(
            text = stringResource(R.string.sign_up),
            onClick = onSignupClicked
        )
    }
}