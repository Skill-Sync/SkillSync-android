package com.ss.skillsync.editprofile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ss.skillsync.commonandroid.components.CircularAsyncImage
import com.ss.skillsync.commonandroid.theme.SkillSyncTheme
import com.ss.skillsync.editprofile.R

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 14/09/2023
 */

@Composable
fun EditPictureSection(
    userImageUrl: String,
    modifier: Modifier = Modifier,
    addPhotoSize: Dp = 14.dp,
) {
    Box(contentAlignment = Alignment.TopCenter, modifier = modifier) {
        CircularAsyncImage(
            imageUrl = userImageUrl,
            contentDescription = "Profile Picture",
            size = 130.dp,
            placeholderPadding = 36.dp,
        )
        Box(
            modifier = Modifier
                .size(addPhotoSize)
                .align(Alignment.BottomEnd)
                .background(color = Color(0xFF335EF7), CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_add_photo),
                contentDescription = "Add",
                tint = Color.White,
                modifier = Modifier.fillMaxSize(0.6f)
            )
        }
    }
}

@Preview
@Composable
fun EditPictureSectionPreview() {
    SkillSyncTheme {
        Box(
            modifier = Modifier.background(color = MaterialTheme.colorScheme.background)
        ) {
            EditPictureSection(userImageUrl = "")
        }
    }
}