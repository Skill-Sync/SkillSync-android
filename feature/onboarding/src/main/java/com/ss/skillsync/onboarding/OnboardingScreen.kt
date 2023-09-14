package com.ss.skillsync.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarDuration.*
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ss.skillsync.commonandroid.theme.SkillSyncTheme
import com.ss.skillsync.onboarding.pages.InterestedSkillsPage
import com.ss.skillsync.onboarding.pages.StrengthsPage

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 08/09/2023
 */
@OptIn(ExperimentalFoundationApi::class)
@Destination
@Composable
fun OnboardingScreen(
    navigator: OnboardingNavigator,
    snackbarHostState: SnackbarHostState,
    viewModel: OnboardingViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    val pagerState = rememberPagerState(pageCount = { 2 })
    LaunchedEffect(key1 = state.currentPageIndex) {
        pagerState.animateScrollToPage(state.currentPageIndex)
    }

    val defaultErrorText = "Something went wrong"
    LaunchedEffect(key1 = state.error) {
        state.error?.let {
            snackbarHostState.showSnackbar(
                message = it.message ?: defaultErrorText,
                duration = SnackbarDuration.Short,
            )
        }
    }

    LaunchedEffect(state.onboardingDone) {
        if (state.onboardingDone) {
            navigator.navigateToHome()
            viewModel.navigatedSuccessfully()
        }
    }

    OnboardingContent(
        state = state,
        onEvent = viewModel::onEvent,
        pagerState = pagerState,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingContent(
    state: OnboardingState,
    onEvent: (OnboardingEvent) -> Unit,
    pagerState: PagerState,
) {
    HorizontalPager(
        state = pagerState,
        userScrollEnabled = false,
        modifier = Modifier.fillMaxSize(),
    ) { pageIndex ->
        when (pageIndex) {
            0 -> InterestedSkillsPage(state, onEvent)
            1 -> StrengthsPage(state, onEvent)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun OnboardingScreenPreview() {
    SkillSyncTheme {
        OnboardingContent(
            state = OnboardingState(),
            onEvent = {},
            pagerState = rememberPagerState(pageCount = { 2 })
        )
    }
}