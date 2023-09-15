package com.ss.skillsync.commonandroid.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ss.skillsync.commonandroid.theme.SkillSyncTheme

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/10/2023.
 */
@Composable
fun Section(
    modifier: Modifier = Modifier,
    header: String? = null,
    headerStartPadding: Dp = 0.dp,
    action: @Composable () -> Unit = {},
    containerColor: Color = MaterialTheme.colorScheme.primaryContainer,
    contentColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    contentPadding: PaddingValues = PaddingValues(16.dp),
    rounded: Boolean = true,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(modifier = modifier) {
        if (header != null) {
            SectionHeader(
                header = header,
                action = action,
                modifier = Modifier.padding(start = headerStartPadding),
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        Card(
            colors = CardDefaults.cardColors(
                containerColor = containerColor,
                contentColor = contentColor,
            ),
            elevation = CardDefaults.cardElevation(0.dp),
            shape = if (rounded) MaterialTheme.shapes.medium else RoundedCornerShape(0.dp),
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier.padding(contentPadding),
                verticalArrangement = Arrangement.Center
            ) {
                content()
            }
        }
    }
}

@Composable
fun SectionHeader(
    modifier: Modifier = Modifier,
    header: String,
    action: @Composable () -> Unit = {},
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = header,
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onBackground,
        )
        Spacer(modifier = Modifier.weight(1f))
        action()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
private fun SectionPrev() {
    SkillSyncTheme {
        Section(
            header = "Session Scheduled",
        ) {
            BrandButton(text = "Hello", onClick = { /*TODO*/ })
            Spacer(modifier = Modifier.height(50.dp))
            Text(text = "Hello")
        }
    }
}
