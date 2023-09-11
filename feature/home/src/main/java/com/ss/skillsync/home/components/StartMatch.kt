package com.ss.skillsync.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ss.skillsync.commonandroid.components.BrandButtonWithIcon
import com.ss.skillsync.commonandroid.theme.SkillSyncTheme
import com.ss.skillsync.commonandroid.R as commonRes

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 11/09/2023
 */
@Composable
fun StartMatchSection(
    onMatchClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
            .background(color = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {
            Text(
                text = "Let's\nMatch",
                style = MaterialTheme.typography.headlineLarge,
                minLines = 2,
                maxLines = 2,
                modifier = Modifier.weight(1f)
            )
            BrandButtonWithIcon(
                text = "Start",
                onClick = onMatchClicked,
                iconPainter = painterResource(id = commonRes.drawable.ic_matching),
                background = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFF672FB6),
                        Color(0xFF4A67E5),
                        Color(0xFF3A86FF),
                    ),
                ),
                cornerRadius = 45.dp,
                iconTintColor = Color.White,
                textColor = Color.White,
                contentPadding = PaddingValues(vertical = 4.dp, horizontal = 24.dp),
                isUppercase = false,
                fontSize = 24.sp,
                iconSize = 45.dp
            )
        }
    }
}

@Preview
@Composable
fun StartMatchSectionPreview() {
    SkillSyncTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            StartMatchSection({})
        }
    }
}