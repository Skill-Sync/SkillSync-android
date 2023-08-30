package com.ss.skillsync.presentation.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.ss.skillsync.R

@OptIn(ExperimentalTextApi::class)
@Composable
fun Slogan(
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 42.sp,
    lineHeight: TextUnit = 41.sp,
    primaryColor: Color = Color.White,
    hasUnderline: Boolean = true
) {
    val textStyle = SpanStyle(
        fontSize = fontSize,
        fontFamily = FontFamily(Font(R.font.montserrat_bold)),
        color = primaryColor,
        letterSpacing = 1.5.sp
    )
    Text(
        text = buildAnnotatedString {
            withStyle(style = ParagraphStyle(lineHeight = lineHeight)) {
                withStyle(style = textStyle) {
                    append("Find Your\n")
                }
                withStyle(
                    style = textStyle.copy(
                        brush = Brush.linearGradient(
                            colorStops = arrayOf(
                                0f to Color(0xFF3A86FF),
                                0.2f to Color(0xFF8338EC),
                                0.65f to Color(0xFFFFBE0B),
                                0.78f to Color(0xFFFB5607)
                            )
                        ),
                        textDecoration = if (hasUnderline) TextDecoration.Underline else TextDecoration.None
                    )
                ) {
                    append("Future skills\n")
                }
                withStyle(style = textStyle) {
                    append("Here!")
                }
            }
        },
        modifier = modifier
    )
}

@Preview
@Composable
fun SloganPreview() {
    Slogan()
}