package com.ss.skillsync.signin

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
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
import com.ss.skillsync.commonandroid.theme.Blue
import com.ss.skillsync.commonandroid.theme.DarkBlue
import com.ss.skillsync.commonandroid.theme.Purple
import com.ss.skillsync.commonandroid.theme.SemiBlack
import com.ss.skillsync.commonandroid.theme.SkillSyncTheme
import com.ss.skillsync.commonandroid.R as comRes

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
        val text = state.error ?: stringResource(comRes.string.something_went_wrong)
        LaunchedEffect(Unit) {
            snackbarHostState.showSnackbar(
                message = text,
                duration = SnackbarDuration.Short,
            )
            viewModel.resetErrors()
        }
    }

    state.navigate(
        toHomeScreen = navigator::navigateToHome,
        toOnboarding = navigator::navigateToOnboarding,
    )

    SignInContent(
        state = state,
        onTypeChanged = viewModel::onTypeChanged,
        onEmailChanged = viewModel::onEmailChanged,
        onPasswordChanged = viewModel::onPasswordChanged,
        onSignInClicked = viewModel::onSignInClicked,
        onSignupClicked = {
            navigator.navigateToSignUp()
        },
    )
}

@Composable
private fun SignInContent(
    state: SignInState,
    onTypeChanged: () -> Unit,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onSignInClicked: () -> Unit = {},
    onSignupClicked: () -> Unit = {},
) {
    val scrollState = rememberScrollState()
    ScreenColumn(
        modifier = Modifier.verticalScroll(scrollState),
        isLoading = state.isLoading,
        screenColor = SemiBlack
    ) {
        HeaderSection()
        SignInForm(
            state = state,
            onEmailChanged = onEmailChanged,
            onPasswordChanged = onPasswordChanged,
            onTypeChanged = onTypeChanged,
        )
        SignInButtonsSection(
            onSignInClicked = onSignInClicked,
            onSignupClicked = onSignupClicked,
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
    onTypeChanged: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        val space = 15.dp
        TypeSwitch(
            modifier = Modifier
                .fillMaxWidth(0.3f)
                .align(Alignment.End),
            type = state.loginType,
            onTypeChanged = onTypeChanged,
        )
        RoundedTextFieldWithTitle(
            title = stringResource(R.string.email),
            value = state.email,
            onValueChange = onEmailChanged,
        )
        Spacer(modifier = Modifier.height(space))
        RoundedPasswordTextField(
            title = stringResource(R.string.password),
            value = state.password,
            onValueChange = onPasswordChanged,
            imeAction = ImeAction.Done,
        )
    }
}

@Composable
private fun SignInButtonsSection(
    modifier: Modifier = Modifier,
    onSignInClicked: () -> Unit,
    onSignupClicked: () -> Unit,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        BrandButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.login),
            onClick = onSignInClicked,
            background = Brush.horizontalGradient(
                listOf(
                    Purple,
                    Blue,
                ),
            ),
            textColor = MaterialTheme.colorScheme.onBackground,
        )
        Spacer(modifier = Modifier.height(10.dp))
        FooterSection(
            onSignupClicked = onSignupClicked,
        )
    }
}

@Composable
private fun TypeSwitch(
    modifier: Modifier = Modifier,
    type: String,
    onTypeChanged: () -> Unit,
) {
    var alignment by remember {
        mutableStateOf(Arrangement.Start)
    }
    Column(modifier = modifier) {
        Text(
            modifier = Modifier.padding(start = 5.dp, bottom = 5.dp),
            text = stringResource(id = R.string.login_as),
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onBackground,
        )
        Spacer(modifier = Modifier.height(2.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outline,
                    shape = MaterialTheme.shapes.small,
                )
                .padding(3.dp),
            horizontalArrangement = alignment,
        ) {
            Text(
                text = type,
                modifier = Modifier
                    .clip(MaterialTheme.shapes.small)
                    .background(
                        color = DarkBlue,
                    )
                    .clickable(
                        role = Role.Switch
                    ) {
                        alignment = if (alignment == Arrangement.Start) {
                            Arrangement.End
                        } else {
                            Arrangement.Start
                        }
                        onTypeChanged()
                    }
                    .padding(10.dp)
                    .animateContentSize(),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}

@Composable
private fun FooterSection(
    modifier: Modifier = Modifier,
    onSignupClicked: () -> Unit,
) {
    Row(
        modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
    ) {
        Text(
            text = stringResource(R.string.you_dont_have_an_account),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Spacer(modifier = Modifier.width(5.dp))
        ClickableText(
            text = stringResource(R.string.sign_up),
            onClick = onSignupClicked,
        )
    }
}


@Preview
@Composable
fun TypeSwitchPrev() {
    SkillSyncTheme {
        TypeSwitch(
            type = "user",
            onTypeChanged = {},
        )
    }
}
