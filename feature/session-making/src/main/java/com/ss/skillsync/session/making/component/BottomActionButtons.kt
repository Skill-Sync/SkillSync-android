package com.ss.skillsync.session.making.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ss.skillsync.commonandroid.components.BrandOutlinedButton
import com.ss.skillsync.commonandroid.components.SecondaryActionBrandButton

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/14/2023.
 */

@Composable
fun ActionButtons(
    modifier: Modifier = Modifier,
    positiveButtonText: String? = null,
    negativeButtonText: String? = null,
    positiveButtonEnabled: Boolean = true,
    negativeButtonEnabled: Boolean = true,
    onPositiveClicked: () -> Unit = {},
    onNegativeClicked: () -> Unit = {},
) {
    val addSpacing = positiveButtonText != null && negativeButtonText != null
    Row(
        modifier = modifier
    ) {
        if (positiveButtonText != null)
            Box(
                modifier = Modifier.weight(1f)
            ) {
                SecondaryActionBrandButton(
                    text = positiveButtonText, onClick = onPositiveClicked,
                    modifier = Modifier.fillMaxWidth(),
                    enabled = positiveButtonEnabled,
                )
            }

        if (addSpacing)
            Spacer(modifier = Modifier.weight(0.1f))

        if (negativeButtonText != null)
            Box(
                modifier = Modifier.weight(1f)
            ) {
                BrandOutlinedButton(
                    text = negativeButtonText, onClick = onNegativeClicked,
                    modifier = Modifier.fillMaxWidth(),
                    enabled = negativeButtonEnabled,
                )
            }
    }
}