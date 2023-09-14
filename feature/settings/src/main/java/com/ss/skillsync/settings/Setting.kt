package com.ss.skillsync.settings

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 14/09/2023
 */
class Setting private constructor(
    val icon: Painter,
    val title: String,
    val itemColor: Color,
    val onClick: (() -> Unit)?,
    val onSwitch: ((Boolean) -> Unit)?,
    val isChecked: Boolean?,
) {

    constructor(
        icon: Painter,
        title: String,
        itemColor: Color,
        onClick: (() -> Unit),
    ) : this(icon, title, itemColor, onClick, null, null)

    constructor(
        icon: Painter,
        title: String,
        itemColor: Color,
        onSwitch: ((Boolean) -> Unit),
        isChecked: Boolean
    ) : this(icon, title, itemColor, null, onSwitch, isChecked)
}