package com.ss.skillsync.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ss.skillsync.commonandroid.components.CircularAsyncImage
import com.ss.skillsync.commonandroid.components.Section
import com.ss.skillsync.commonandroid.theme.SkillSyncTheme
import com.ss.skillsync.home.R
import com.ss.skillsync.model.Mentor

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 10/09/2023
 */
@Composable
fun MentorsSlider(
    mentorsList: List<Mentor>,
    onMentorClicked: (Mentor) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (mentorsList.isEmpty()) {
        return
    }
    Section(
        modifier = modifier,
        contentPadding = PaddingValues(0.dp),
        header = stringResource(R.string.top_mentors),
        headerStartPadding = 16.dp,
        containerColor = MaterialTheme.colorScheme.background,
        rounded = false
    ) {
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            items(mentorsList, key = { it.id }) { mentor ->
                MentorHead(
                    mentor = mentor,
                    onMentorClicked = {
                        onMentorClicked(mentor)
                    }
                )
            }
        }
    }
}

@Composable
fun MentorHead(mentor: Mentor, onMentorClicked: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .clickable(onClick = onMentorClicked),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CircularAsyncImage(
            imageUrl = mentor.pictureUrl,
            contentDescription = mentor.name,
            size = 64.dp,
        )
        Text(
            text = mentor.name,
            style = MaterialTheme.typography.labelLarge.copy(fontSize = 12.sp),
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = mentor.field,
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 8.sp),
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Preview
@Composable
fun MentorsSliderPreview() {
    SkillSyncTheme {
        Box(modifier = Modifier.height(200.dp)) {
            MentorsSlider(
                List(10) {
                    Mentor(
                        it.toString(),
                        "Mohannad El-Sayeh",
                        "https://picsum.photos/200",
                        "Software Engineer",
                    )
                },
                onMentorClicked = {}
            )
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
                    1.toString(),
                    "Mohannad El-Sayeh",
                    "https://picsum.photos/200",
                    "Software Engineer",
                ),
                {}
            )
        }
    }
}