package com.ss.skillsync.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    viewModel: OnboardingViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    val pagerState = rememberPagerState(pageCount = { 2 })
    LaunchedEffect(key1 = state.currentPageIndex) {
        pagerState.animateScrollToPage(state.currentPageIndex)
    }
    if(state.onboardingDone) {
        navigator.navigateToHome()
        viewModel.navigatedSuccessfully()
    }
    HorizontalPager(
        state = pagerState,
        userScrollEnabled = false,
        modifier = Modifier.fillMaxSize(),
    ) { pageIndex ->
        Box(modifier = Modifier.fillMaxSize()) {
            when (pageIndex) {
                0 -> InterestedSkillsPage()
                1 -> StrengthsPage()
            }
            if (state.isBackVisible) {
                IconButton(
                    onClick = {
                        viewModel.onEvent(OnboardingEvent.BackClicked)
                    },
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(top = 16.dp, start = 16.dp)
                ) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }
    }

}

@Preview
@Composable
fun OnboardingScreenPreview() {
    SkillSyncTheme {
        OnboardingScreen(object : OnboardingNavigator {
            override fun navigateToHome() {/* Placeholder */}
        })
    }
}