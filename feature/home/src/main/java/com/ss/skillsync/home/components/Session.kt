package com.ss.skillsync.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ss.skillsync.commonandroid.components.CircularAsyncImage
import com.ss.skillsync.commonandroid.components.Section
import com.ss.skillsync.commonandroid.theme.SkillSyncTheme
import com.ss.skillsync.model.Session

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/10/2023.
 */

@Composable
fun SessionsScheduledList(
    sessions: List<Session>,
    onSessionClicked: (Session) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(vertical = 20.dp, horizontal = 12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(sessions, key = { it.sessionId }) {
            SessionScheduledItem(
                session = it,
                onSessionClicked = {
                    onSessionClicked(it)
                },
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SessionScheduledItem(
    session: Session,
    onSessionClicked: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.onSecondaryContainer,
) {
    val subTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = containerColor),
        onClick = onSessionClicked,
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            CircularAsyncImage(
                modifier = Modifier.weight(1f),
                imageUrl = session.image,
                contentDescription = session.name,
                size = 60.dp
            )
            Column(
                modifier = Modifier.weight(2f)
            ) {
                Text(
                    text = session.name,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                )
                if (session.isSkillAvailable) {
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = session.skill,
                        style = MaterialTheme.typography.bodyMedium,
                        color = subTextColor
                    )
                }
            }
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = session.scheduledHour,
                    style = MaterialTheme.typography.bodyMedium,
                    color = subTextColor
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = session.scheduledDate,
                    style = MaterialTheme.typography.bodyMedium,
                    color = subTextColor
                )
            }
        }
    }
}


@Preview
@Composable
fun ScheduledSessionPrev() {
    SkillSyncTheme {
        val session = Session(
            "saDAS@!",
            "Mohannad El-Sayeh",
            "https://picsum.photos/200",
            "Software Engineer",
            "3:00 PM",
            scheduledDate = "12/12/2021",
        )

        SessionScheduledItem(session = session, onSessionClicked = {})
    }
}

@Preview
@Composable
fun UserScheduledListPrev() {
    val sessions = List(10) {
        Session(
            "saDAS@!$it",
            "Mohannad El-Sayeh",
            "https://picsum.photos/200",
            "Software Engineer",
            "3:00 PM",
            scheduledDate = "12/12/2021",
        )
    }

    SkillSyncTheme {
        Section(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentPadding = 0.dp
        ) {
            SessionsScheduledList(sessions = sessions, {})
        }
    }
}