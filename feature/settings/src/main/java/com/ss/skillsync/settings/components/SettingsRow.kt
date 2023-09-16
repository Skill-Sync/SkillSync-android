package com.ss.skillsync.settings.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ss.skillsync.commonandroid.theme.SkillSyncTheme
import com.ss.skillsync.settings.R

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 14/09/2023
 */
@Composable
fun SettingsItem(
    icon: Painter,
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    itemColor: Color = Color.White,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(32.dp)
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = null,
                onClick = onClick
            )
            .then(modifier),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Icon(
            painter = icon,
            contentDescription = null,
            tint = itemColor,
            modifier = Modifier.size(32.dp)
        )
        Text(
            text = title,
            style = MaterialTheme.typography.labelLarge.copy(fontSize = 16.sp),
            color = itemColor,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 26.dp)
        )
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = null,
            tint = itemColor,
            modifier = Modifier.size(18.dp)
        )
    }
}

@Composable
fun SwitchableSettingsItem(
    icon: Painter,
    title: String,
    isChecked: Boolean,
    onCheckChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    itemColor: Color = Color.White,
    switchColors: SwitchColors = SwitchDefaults.colors(
        checkedThumbColor = Color(0xFFD9D9D9),
        checkedTrackColor = Color(0xFF335EF7),
    ),
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(32.dp)
            .clickable(
                onClick = { onCheckChange(!isChecked) },
                indication = null,
                interactionSource = MutableInteractionSource()
            )
            .then(modifier),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Icon(
            painter = icon,
            contentDescription = null,
            tint = itemColor,
            modifier = Modifier.size(32.dp)
        )
        Text(
            text = title,
            style = MaterialTheme.typography.labelLarge.copy(fontSize = 16.sp),
            color = itemColor,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 26.dp)
        )
        Switch(
            checked = isChecked,
            onCheckedChange = onCheckChange,
            colors = switchColors,
        )
    }
}

@Preview
@Composable
fun SwitchableSettingsItemPreview() {
    SkillSyncTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            SwitchableSettingsItem(
                icon = painterResource(id = R.drawable.ic_dark),
                title = "Dark Mode",
                isChecked = true,
                onCheckChange = {},
            )
        }
    }
}

@Preview
@Composable
fun SettingsItemPreview() {
    SkillSyncTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            SettingsItem(
                rememberVectorPainter(image = Icons.Outlined.AccountCircle),
                "Edit Profile",
                onClick = {}
            )
        }
    }
}