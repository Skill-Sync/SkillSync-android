package com.ss.skillsync.commonandroid.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import com.ss.skillsync.commonandroid.theme.DoveGray

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/7/2023.
 */

@Composable
fun HeaderLargeText(
    modifier: Modifier = Modifier,
    text: String,
) {
    Text(
        modifier = modifier.fillMaxWidth(),
        text = text,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.headlineLarge,
        color = MaterialTheme.colorScheme.onBackground
    )
}

@Composable
fun SubHeaderText(
    modifier: Modifier = Modifier,
    text: String,
) {
    Text(
        modifier = modifier.fillMaxWidth(),
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        color = DoveGray,
        textAlign = TextAlign.Center
    )
}

@Composable
fun ClickableText(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) {
    Text(
        modifier = modifier.clickable {
            onClick()
        },
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.tertiary,
        textDecoration = TextDecoration.Underline
    )
}