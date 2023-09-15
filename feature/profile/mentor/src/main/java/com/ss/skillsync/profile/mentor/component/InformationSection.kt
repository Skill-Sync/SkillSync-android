package com.ss.skillsync.profile.mentor.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ss.skillsync.commonandroid.components.HeaderLargeText
import com.ss.skillsync.commonandroid.theme.LightGray
import com.ss.skillsync.commonandroid.theme.SkillSyncTheme
import com.ss.skillsync.profile.mentor.R

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/12/2023.
 */

@Composable
fun InformationSection(
    modifier: Modifier = Modifier,
    name: String,
    description: String,
    skill: String,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            HeaderLargeText(
                modifier = Modifier.weight(1f),
                text = name, fontSize = 35.sp,
                textAlign = TextAlign.Start
            )
            RatingIcon(rating = 4.5)
        }
        Text(
            text = skill, style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(18.dp))
        if (description.isNotBlank())
            SectionWithText(header = stringResource(id = R.string.about), text = description)
    }
}

@Composable
fun RatingIcon(
    modifier: Modifier = Modifier,
    rating: Double = 4.5,
) {

    Row(
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
            .padding(6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_star),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = "$rating",
            style = MaterialTheme.typography.labelMedium,
        )

    }
}

@Composable
fun SectionWithText(
    modifier: Modifier = Modifier,
    header: String,
    text: String,
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = header, style = MaterialTheme.typography.labelLarge,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = text, style = MaterialTheme.typography.bodyLarge,
            color = LightGray
        )
    }
}

@Preview
@Composable
private fun InformationSectionPreview() {
    SkillSyncTheme {
        InformationSection(
            name = "Mohammed Salman",
            description = "Android Compose is a great way to kill yourself very easy",
            skill = "Killing My Self"
        )
    }
}
