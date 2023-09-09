package com.ss.skillsync.onboarding.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.ss.skillsync.commonandroid.components.BrandButton
import com.ss.skillsync.commonandroid.theme.DoveGray
import com.ss.skillsync.commonandroid.theme.Orange
import com.ss.skillsync.commonandroid.theme.Scorpion
import com.ss.skillsync.commonandroid.theme.White
import com.ss.skillsync.commonandroid.theme.Yellow

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 09/09/2023
 */

@Composable
fun OnboardingButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier, enabled: Boolean = true) {
    BrandButton(
        text = text,
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth(),
        background = Brush.horizontalGradient(
            listOf(
                Orange,
                Yellow,
            ),
        ),
        enabled = enabled,
        textColor = White,
        disabledBackgroud = Brush.horizontalGradient(
            listOf(
                DoveGray,
                Scorpion
            )
        ),
        disabledTextColor = Color.Black.copy(alpha = 0.8f)
    )

}