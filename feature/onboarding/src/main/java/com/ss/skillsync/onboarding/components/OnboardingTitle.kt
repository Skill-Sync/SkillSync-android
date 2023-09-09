package com.ss.skillsync.onboarding.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ss.skillsync.commonandroid.components.HeaderLargeText
import com.ss.skillsync.commonandroid.components.SubHeaderText

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 09/09/2023
 */
@Composable
fun OnboardingTitle(
    header: String,
    subHeader: String,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        HeaderLargeText(text = header)
        SubHeaderText(text = subHeader, style = MaterialTheme.typography.headlineMedium)
    }
}