package com.ss.skillsync.commonandroid.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ss.skillsync.commonandroid.R
import com.ss.skillsync.commonandroid.theme.SkillSyncTheme

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/10/2023.
 */
@Composable
fun WelcomeCard(
    modifier: Modifier = Modifier,
    imageUrl: String,
    name: String,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CircularAsyncImage(imageUrl = imageUrl, contentDescription = name)
        Spacer(modifier = modifier.width(14.dp))
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Welcome 👋",
                    style = MaterialTheme.typography.labelMedium.copy(fontSize = 14.sp),
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
            Text(
                text = name,
                style = MaterialTheme.typography.labelLarge.copy(fontSize = 18.sp),
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
    }
}

@Composable
fun CircularAsyncImage(
    imageUrl: String,
    contentDescription: String,
    modifier: Modifier = Modifier,
    size: Dp = 50.dp,
) {
    var imagePadding by remember {
        mutableStateOf(12.dp)
    }

    Box(modifier = modifier) {
        Box(
            modifier = Modifier
                .size(size)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.onBackground),
            contentAlignment = Alignment.Center,
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = contentDescription,
                modifier = Modifier
                    .size(size)
                    .padding(imagePadding),
                placeholder = painterResource(id = R.drawable.ic_empty_image),
                error = painterResource(id = R.drawable.ic_empty_image),
                contentScale = ContentScale.Crop,
                onSuccess = {
                    imagePadding = 0.dp
                },
            )
        }
    }
}

@Preview
@Composable
private fun WelcomeCardPrev() {
    SkillSyncTheme {
        ScreenColumn {
            WelcomeCard(
                imageUrl = "https://placebear.com/g/400/400",
                name = "Salman",
                onClick = {},
            )
        }
    }
}
