package com.ss.skillsync.settings.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ss.skillsync.commonandroid.theme.SkillSyncTheme
import com.ss.skillsync.settings.Setting

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 14/09/2023
 */

@Composable
fun SettingsList(
    settingsList: List<Setting>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(28.dp),
    ) {
        items(settingsList, key = { it.title }) { setting ->
            if (setting.onClick == null) {
                SwitchableSettingsItem(
                    icon = setting.icon,
                    title = setting.title,
                    isChecked = setting.isChecked!!,
                    onCheckChange = setting.onSwitch!!,
                )
            } else {
                SettingsItem(
                    icon = setting.icon,
                    title = setting.title,
                    itemColor = setting.itemColor,
                    onClick = setting.onClick,
                )
            }
        }
    }
}

@Preview
@Composable
fun SettingsListPreview() {
    SkillSyncTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            SettingsList(
                List(6) {
                    Setting(
                        icon = painterResource(id = android.R.drawable.ic_menu_camera),
                        title = "Setting $it",
                        itemColor = MaterialTheme.colorScheme.onSurface,
                        onClick = {  },
                    )
                }
            )
        }
    }
}