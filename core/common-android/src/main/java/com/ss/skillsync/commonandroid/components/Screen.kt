package com.ss.skillsync.commonandroid.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.ss.skillsync.commonandroid.theme.SkillSyncTheme

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/7/2023.
 */

@Composable
private fun BasicScreen(
    isLoading: Boolean,
    isPausedWhileLoading: Boolean,
    contentPadding: PaddingValues = PaddingValues(16.dp),
    screenColor: Color = MaterialTheme.colorScheme.background,
    isBackDisplayed: Boolean,
    screenLabel: String?,
    onBackClicked: () -> Unit,
    content: @Composable BoxScope.() -> Unit,
) {
    SkillSyncTheme(backgroundColor = screenColor) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            containerColor = MaterialTheme.colorScheme.background,
            topBar = {
                if (isBackDisplayed || screenLabel != null) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, top = 20.dp, end = 16.dp)
                    ) {
                        if (isBackDisplayed) {
                            IconButton(
                                onClick = onBackClicked,
                                modifier = Modifier.align(Alignment.CenterStart)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = "Back",
                                    tint = MaterialTheme.colorScheme.onBackground,
                                )
                            }
                        }
                        screenLabel?.let {
                            Text(
                                text = screenLabel,
                                style = MaterialTheme.typography.displayMedium,
                                color = MaterialTheme.colorScheme.onBackground,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                }
            }
        ) { scaffoldPadding ->
            Box(
                modifier = Modifier
                    .padding(scaffoldPadding)
                    .padding(contentPadding)
            ) {
                content()
                AnimatedVisibility(isLoading, enter = fadeIn(), exit = fadeOut()) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.background.copy(alpha = 0.5f)),
                        contentAlignment = Alignment.Center,
                    ) {
                        if (isPausedWhileLoading) {
                            Dialog(onDismissRequest = { }) {
                                LoadingIndicator(
                                    isVisible = isLoading,
                                    modifier = Modifier.fillMaxSize(0.4f)
                                )
                            }
                        } else {
                            LoadingIndicator(
                                isVisible = isLoading,
                                modifier = Modifier.fillMaxSize(0.4f)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ScreenColumn(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    isPausedWhileLoading: Boolean = true,
    arrangement: Arrangement.Vertical = Arrangement.SpaceEvenly,
    contentPadding: PaddingValues = PaddingValues(16.dp),
    screenColor: Color = MaterialTheme.colorScheme.background,
    isScrollable: Boolean = false,
    onScrollState: (scrollState: ScrollState) -> Unit = {},
    screenLabel: String? = null,
    isBackDisplayed: Boolean = false,
    onBackClicked: () -> Unit = {},
    content: @Composable ColumnScope.() -> Unit = {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                text = "Not implemented yet.",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    },
) {
    val scrollModifier = if (isScrollable) {
        val scrollState = rememberScrollState()
        onScrollState(scrollState)
        Modifier
            .verticalScroll(scrollState)
            .then(modifier)
    } else {
        modifier
    }
    BasicScreen(
        isLoading = isLoading,
        isPausedWhileLoading = isPausedWhileLoading,
        contentPadding = contentPadding,
        screenColor = screenColor,
        isBackDisplayed = isBackDisplayed,
        screenLabel = screenLabel,
        onBackClicked = onBackClicked,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .then(scrollModifier),
            verticalArrangement = arrangement,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            content()
        }
    }
}
