package com.ss.skillsync.commonandroid.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
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
    contentPadding: PaddingValues = PaddingValues(16.dp),
    screenColor: Color = MaterialTheme.colorScheme.background,
    content: @Composable BoxScope.() -> Unit,
) {
    SkillSyncTheme(backgroundColor = screenColor) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(contentPadding),
        ) {
            AnimatedContent(targetState = isLoading, label = "") { isLoading ->
                if (isLoading) {
                    Dialog(onDismissRequest = { }) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                }
                content()
            }
        }
    }
}

@Composable
fun ScreenColumn(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    contentPadding: PaddingValues = PaddingValues(16.dp),
    screenColor: Color = MaterialTheme.colorScheme.background,
    content: @Composable ColumnScope.() -> Unit,
) {
    BasicScreen(
        isLoading = isLoading,
        contentPadding = contentPadding,
        screenColor = screenColor,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .then(modifier),
            content = content,
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
        )
    }
}
