package com.ss.skillsync.commonandroid.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ss.skillsync.commonandroid.theme.Blue
import com.ss.skillsync.commonandroid.theme.Orange
import com.ss.skillsync.commonandroid.theme.Purple
import com.ss.skillsync.commonandroid.theme.SkillSyncTheme
import com.ss.skillsync.commonandroid.theme.White
import com.ss.skillsync.commonandroid.theme.Yellow

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 31/08/2023
 */

@Composable
fun BrandButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    background: Brush = Brush.linearGradient(colors = listOf(Color.White, Color.White)),
    textColor: Color = Color.Black,
    iconPainter: Painter? = null,
    iconTintColor: Color? = textColor,
    iconSize: Int = 40,
    isUppercase: Boolean = true,
    contentPadding: PaddingValues = PaddingValues(vertical = 16.dp, horizontal = 24.dp)
) {
    Button(
        onClick = onClick,
        shape = MaterialTheme.shapes.small,
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
    ) {
        Row(
            modifier = Modifier
                .background(background)
                .padding(contentPadding)
                .then(modifier),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (iconPainter != null) {
                if (iconTintColor == null)
                    Image(
                        painter = iconPainter,
                        contentDescription = null,
                        modifier = Modifier
                            .size(iconSize.dp)
                            .padding(end = 16.dp),
                    )
                else
                    Icon(
                        painter = iconPainter,
                        contentDescription = null,
                        tint = iconTintColor,
                        modifier = Modifier
                            .size(iconSize.dp)
                            .padding(end = 16.dp),
                    )
            }
            Text(
                text = if (isUppercase) text.uppercase() else text,
                style = MaterialTheme.typography.labelLarge,
                color = textColor,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
fun ThemeButtonPreview() {
    SkillSyncTheme {
        BrandButton(
            "Find Your Skills",
            {},
            modifier = Modifier.fillMaxWidth(),
            isUppercase = false
        )
    }
}

@Preview
@Composable
fun ColorfulThemeButtonPreview() {
    SkillSyncTheme {
        BrandButton(
            "Login",
            {},
            modifier = Modifier.fillMaxWidth(),
            background = Brush.horizontalGradient(
                listOf(
                    Purple,
                    Blue
                )
            ),
            textColor = White
        )
    }
}

@Preview
@Composable
fun ColorfulThemeButtonPreviewWithIcon() {
    SkillSyncTheme {
        BrandButton(
            "Signup",
            {},
            modifier = Modifier.fillMaxWidth(),
            background = Brush.horizontalGradient(
                listOf(
                    Orange,
                    Yellow
                )
            ),
            textColor = White,
            iconPainter = rememberVectorPainter(image = Icons.Default.Lock)
        )
    }
}