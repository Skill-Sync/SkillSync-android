package com.ss.skillsync.signup

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
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ss.skillsync.commonandroid.components.BrandButton
import com.ss.skillsync.commonandroid.components.ClickableText
import com.ss.skillsync.commonandroid.components.HeaderLargeText
import com.ss.skillsync.commonandroid.components.RoundedPasswordTextField
import com.ss.skillsync.commonandroid.components.RoundedTextFieldWithTitle
import com.ss.skillsync.commonandroid.components.ScreenColumn
import com.ss.skillsync.commonandroid.components.SubHeaderText
import com.ss.skillsync.commonandroid.theme.Orange
import com.ss.skillsync.commonandroid.theme.SkillSyncTheme
import com.ss.skillsync.commonandroid.theme.Yellow

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 8/30/2023.
 */
interface SignupNavigator {
    fun navigateToLogin()
    fun navigateToOnboarding()
}

@Destination
@Composable
fun SignupScreen(
    navigator: SignupNavigator,
    snackbarHostState: SnackbarHostState,
    viewModel: SignupViewModel = hiltViewModel(),
) {

    val state by viewModel.state.collectAsState()

    if (state.isSignupSuccessful) {
        SuccessfulSignUp(snackbarHostState = snackbarHostState) {
            viewModel.reset()
            navigator.navigateToLogin()
        }
    }

    SkillSyncTheme {
        SignupContent(
            state = state,
            onFullNameChanged = viewModel::onFullNameChanged,
            onEmailChanged = viewModel::onEmailChanged,
            onPasswordChanged = viewModel::onPasswordChanged,
            onConfirmPasswordChanged = viewModel::onConfirmPasswordChanged,
            onSignupClicked = viewModel::onSignupClicked,
            onSignInClicked = { navigator.navigateToLogin() }
        )
    }
}

@Composable
private fun SignupContent(
    state: SignupState,
    onFullNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onConfirmPasswordChanged: (String) -> Unit,
    onSignupClicked: () -> Unit,
    onSignInClicked: () -> Unit = {},
) {
    ScreenColumn {
        HeaderSection()
        //Spacer(modifier = Modifier.height(20.dp))
        SignupForm(
            state = state,
            onFullNameChanged = onFullNameChanged,
            onEmailChanged = onEmailChanged,
            onPasswordChanged = onPasswordChanged,
            onConfirmPasswordChanged = onConfirmPasswordChanged,
        )
        //Spacer(modifier = Modifier.height(30.dp))
        SignupButtonsSection(
            onSignupClicked = onSignupClicked
        )
        //Spacer(modifier = Modifier.height(20.dp))
        FooterSection(
            onSignInClicked = onSignInClicked
        )
    }
}

@Composable
private fun HeaderSection(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        HeaderLargeText(text = stringResource(R.string.create_an_account))
        Spacer(modifier = Modifier.height(10.dp))
        SubHeaderText(text = stringResource(R.string.sign_up_to_start_your_learning_journey))
    }
}

@Composable
private fun SignupForm(
    modifier: Modifier = Modifier,
    state: SignupState,
    onFullNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onConfirmPasswordChanged: (String) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        val space = 15.dp
        RoundedTextFieldWithTitle(
            title = stringResource(R.string.full_name),
            value = state.fullName,
            onValueChange = onFullNameChanged
        )
        Spacer(modifier = Modifier.height(space))
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
        Spacer(modifier = Modifier.height(space))
        RoundedPasswordTextField(
            title = stringResource(R.string.confirm_password),
            value = state.confirmPassword,
            onValueChange = onConfirmPasswordChanged
        )
    }
}

@Composable
private fun SignupButtonsSection(
    modifier: Modifier = Modifier,
    onSignupClicked: () -> Unit,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        BrandButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.sign_up),
            onClick = onSignupClicked,
            background = Brush.horizontalGradient(
                listOf(
                    Orange,
                    Yellow
                )
            ),
            textColor = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
private fun FooterSection(
    modifier: Modifier = Modifier,
    onSignInClicked: () -> Unit,
) {
    Row(
        modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.you_have_an_account),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.width(5.dp))
        ClickableText(
            text = stringResource(R.string.sign_in),
            onClick = onSignInClicked
        )
    }
}

@Composable
private fun SuccessfulSignUp(
    snackbarHostState: SnackbarHostState,
    onFinish: () -> Unit = {},
) {
    SkillSyncTheme {
        val text = stringResource(R.string.signup_successful)
        LaunchedEffect(Unit) {
            val result = snackbarHostState.showSnackbar(
                message = text,
                duration = SnackbarDuration.Short
            )
            if (result == SnackbarResult.Dismissed) {
                onFinish()
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun SignupPrev() {
    SkillSyncTheme {
        SignupContent(
            state = SignupState(),
            onFullNameChanged = {},
            onEmailChanged = {},
            onPasswordChanged = {},
            onConfirmPasswordChanged = {},
            onSignupClicked = {}
        )
    }
}