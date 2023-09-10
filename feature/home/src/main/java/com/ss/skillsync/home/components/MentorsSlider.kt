package com.ss.skillsync.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ss.skillsync.commonandroid.theme.SkillSyncTheme
import com.ss.skillsync.model.Mentor

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 10/09/2023
 */
@Composable
fun MentorsSlider() {

}

@Composable
fun MentorHead(mentor: Mentor, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(model = mentor.pictureUrl, contentDescription = mentor.name)
        Text(
            text = mentor.name,
            style = MaterialTheme.typography.labelLarge.copy(fontSize = 8.sp),
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = mentor.field,
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 6.sp),
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Preview
@Composable
fun MentorsSliderPreview() {
    SkillSyncTheme {
        Box(modifier = Modifier.height(200.dp)) {
            MentorsSlider()
        }
    }
}

@Preview
@Composable
fun MentorHeadPreview() {
    SkillSyncTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(MaterialTheme.colorScheme.background)
        ) {
            MentorHead(
                Mentor(
                    "Mohannad El-Sayeh",
                    "Software Engineer",
                    "https://picsum.photos/200",
                )
            )
        }
    }
}