package com.ss.skillsync.profile.mentor.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ss.skillsync.commonandroid.components.RectangleAsyncImage

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/12/2023.
 */

@Composable
fun ProfileImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
) {
    RectangleAsyncImage(
        imageUrl = imageUrl,
        contentDescription = "Profile image",
        modifier = modifier.fillMaxWidth()
    )
}