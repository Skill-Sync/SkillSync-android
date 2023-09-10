package com.ss.skillsync.commonandroid.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ss.skillsync.commonandroid.R

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
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircleImage(imageUrl = imageUrl, contentDescription = name)
        Spacer(modifier = modifier.width(8.dp))
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Welcome",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = modifier.width(2.dp))
                Image(
                    painterResource(id = R.drawable.waving_hand),
                    contentDescription = "Waving Hand",
                    modifier = Modifier.size(16.dp)
                )
            }
            Text(
                text = name, style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Composable
fun CircleImage(
    modifier: Modifier = Modifier,
    imageUrl: String,
    contentDescription: String,
    size: Dp = 50.dp,
) {
    val request = ImageRequest.Builder(LocalContext.current)
        .data(imageUrl)
        .crossfade(true)
        .placeholder(R.drawable.ic_empty_image)
        .error(R.drawable.ic_empty_image)
        .build()

    Box(modifier = modifier) {
        Box(
            modifier = Modifier
                .size(size)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.onBackground),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = request, contentDescription = contentDescription,
                modifier = Modifier.size(size)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun WelcomeCardPrev() {
    ScreenColumn {
        WelcomeCard(
            imageUrl = "https://placebear.com/g/400/400",
            name = "Salman"
        ) {

        }
    }

}