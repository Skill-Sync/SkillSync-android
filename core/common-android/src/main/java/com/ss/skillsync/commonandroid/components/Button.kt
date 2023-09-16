package com.ss.skillsync.commonandroid.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ss.skillsync.commonandroid.theme.Blue
import com.ss.skillsync.commonandroid.theme.DoveGray
import com.ss.skillsync.commonandroid.theme.Orange
import com.ss.skillsync.commonandroid.theme.Purple
import com.ss.skillsync.commonandroid.theme.Scorpion
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
    disabledBackgroud: Brush = Brush.linearGradient(colors = listOf(Scorpion, Scorpion)),
    textColor: Color = Color.Black,
    disabledTextColor: Color = DoveGray,
    isUppercase: Boolean = true,
    contentPadding: PaddingValues = PaddingValues(vertical = 16.dp, horizontal = 24.dp),
    enabled: Boolean = true,
) {
    Button(
        onClick = onClick,
        shape = MaterialTheme.shapes.small,
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        enabled = enabled,
    ) {
        Row(
            modifier = modifier
                .background(
                    if (enabled) {
                        background
                    } else {
                        disabledBackgroud
                    },
                )
                .padding(contentPadding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                text = if (isUppercase) text.uppercase() else text,
                style = MaterialTheme.typography.labelLarge,
                color = if (enabled) textColor else disabledTextColor,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
fun PrimaryActionBrandButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    disabledBackgroud: Brush = Brush.linearGradient(colors = listOf(Scorpion, Scorpion)),
    disabledTextColor: Color = DoveGray,
    contentPadding: PaddingValues = PaddingValues(vertical = 16.dp, horizontal = 24.dp),
    enabled: Boolean = true,
) {
    BrandButton(
        text = text, onClick = onClick,
        modifier = modifier,
        background = Brush.horizontalGradient(
            listOf(
                Orange,
                Yellow,
            ),
        ),
        disabledBackgroud = disabledBackgroud,
        textColor = White,
        disabledTextColor = disabledTextColor,
        isUppercase = false,
        contentPadding = contentPadding,
        enabled = enabled,
    )
}

@Composable
fun SecondaryActionBrandButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    disabledBackgroud: Brush = Brush.linearGradient(colors = listOf(Scorpion, Scorpion)),
    disabledTextColor: Color = DoveGray,
    contentPadding: PaddingValues = PaddingValues(vertical = 16.dp, horizontal = 24.dp),
    enabled: Boolean = true,
) {
    BrandButton(
        text = text, onClick = onClick,
        modifier = modifier,
        background = Brush.horizontalGradient(
            listOf(
                Purple,
                Blue,
            ),
        ),
        disabledBackgroud = disabledBackgroud,
        textColor = White,
        disabledTextColor = disabledTextColor,
        isUppercase = false,
        contentPadding = contentPadding,
        enabled = enabled,
    )
}

@Composable
fun BrandButtonWithIcon(
    text: String,
    onClick: () -> Unit,
    iconPainter: Painter,
    modifier: Modifier = Modifier,
    background: Brush = Brush.linearGradient(colors = listOf(Color.White, Color.White)),
    disabledBackgroud: Brush = Brush.linearGradient(colors = listOf(Scorpion, Scorpion)),
    textColor: Color = Color.Black,
    disabledTextColor: Color = DoveGray,
    iconTintColor: Color? = textColor,
    iconSize: Dp = 40.dp,
    isUppercase: Boolean = true,
    contentPadding: PaddingValues = PaddingValues(vertical = 16.dp, horizontal = 24.dp),
    enabled: Boolean = true,
    cornerRadius: Dp? = null,
    fontSize: TextUnit = 14.sp,
) {
    Button(
        onClick = onClick,
        shape = if (cornerRadius == null) {
            MaterialTheme.shapes.small
        } else {
            RoundedCornerShape(
                cornerRadius,
            )
        },
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        enabled = enabled,
    ) {
        Row(
            modifier = modifier
                .background(
                    if (enabled) {
                        background
                    } else {
                        disabledBackgroud
                    },
                )
                .padding(contentPadding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            if (iconTintColor == null) {
                Image(
                    painter = iconPainter,
                    contentDescription = null,
                    modifier = Modifier
                        .size(iconSize)
                        .padding(end = 16.dp),
                )
            } else {
                Icon(
                    painter = iconPainter,
                    contentDescription = null,
                    tint = iconTintColor,
                    modifier = Modifier
                        .size(iconSize)
                        .padding(end = 16.dp),
                )
            }
            Text(
                text = if (isUppercase) text.uppercase() else text,
                style = MaterialTheme.typography.labelLarge,
                color = if (enabled) textColor else disabledTextColor,
                textAlign = TextAlign.Center,
                fontSize = fontSize,
            )
        }
    }
}

@Composable
fun BrandOutlinedButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    contentPadding: PaddingValues = PaddingValues(vertical = 16.dp, horizontal = 24.dp),
    onClick: () -> Unit,
) {
    OutlinedButton(
        onClick = onClick,
        shape = MaterialTheme.shapes.small,
        contentPadding = PaddingValues(0.dp),
        border = BorderStroke(1.dp, if (enabled) MaterialTheme.colorScheme.primary else DoveGray),
        enabled = enabled,
    ) {
        Row(
            modifier = modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(contentPadding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.labelLarge,
                textAlign = TextAlign.Center,
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
            isUppercase = false,
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
                    Blue,
                ),
            ),
            textColor = White,
        )
    }
}

@Preview
@Composable
fun ColorfulThemeButtonPreviewWithIcon() {
    SkillSyncTheme {
        BrandButtonWithIcon(
            "Signup",
            {},
            modifier = Modifier.fillMaxWidth(),
            background = Brush.horizontalGradient(
                listOf(
                    Orange,
                    Yellow,
                ),
            ),
            textColor = White,
            iconPainter = rememberVectorPainter(image = Icons.Default.Lock),
        )
    }
}
