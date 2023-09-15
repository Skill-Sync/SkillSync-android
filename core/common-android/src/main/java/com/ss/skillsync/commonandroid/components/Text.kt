package com.ss.skillsync.commonandroid.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import com.ss.skillsync.commonandroid.theme.DoveGray

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/7/2023.
 */

@Composable
fun HeaderLargeText(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = MaterialTheme.typography.headlineLarge.fontSize,
    textAlign: TextAlign = TextAlign.Center,
) {
    Text(
        modifier = modifier.fillMaxWidth(),
        text = text,
        textAlign = textAlign,
        style = MaterialTheme.typography.headlineLarge.copy(fontSize = fontSize),
        color = MaterialTheme.colorScheme.onBackground,
    )
}

@Composable
fun SubHeaderText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
    textAlign: TextAlign = TextAlign.Center,
) {
    Text(
        modifier = modifier.fillMaxWidth(),
        text = text,
        style = style,
        color = DoveGray,
        textAlign = textAlign,
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
        textDecoration = TextDecoration.Underline,
    )
}
