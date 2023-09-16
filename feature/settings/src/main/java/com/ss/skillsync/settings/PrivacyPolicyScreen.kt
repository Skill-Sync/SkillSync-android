package com.ss.skillsync.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ramcosta.composedestinations.annotation.Destination
import com.ss.skillsync.commonandroid.BaseNavigator
import com.ss.skillsync.commonandroid.components.ScreenColumn
import com.ss.skillsync.commonandroid.theme.SkillSyncTheme

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 14/09/2023
 */

@Destination
@Composable
fun PrivacyPolicyScreen(
    navigator: BaseNavigator,
) {
    ScreenColumn(isBackDisplayed = true, onBackClicked = { navigator.popBackStack() })
}

@Preview
@Composable
fun PrivacyPolicyScreenPreview() {
    SkillSyncTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            PrivacyPolicyScreen(object : BaseNavigator {
                override fun popBackStack() {
                }
            })
        }
    }
}