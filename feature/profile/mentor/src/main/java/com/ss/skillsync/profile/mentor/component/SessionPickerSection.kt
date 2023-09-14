package com.ss.skillsync.profile.mentor.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ss.skillsync.commonandroid.components.ScreenColumn
import com.ss.skillsync.commonandroid.theme.DarkPurple
import com.ss.skillsync.commonandroid.theme.Purple
import com.ss.skillsync.commonandroid.theme.SkillSyncTheme
import com.ss.skillsync.model.Day
import com.ss.skillsync.model.Session
import com.ss.skillsync.model.SessionDays
import com.ss.skillsync.profile.mentor.R
import com.ss.skillsync.profile.mentor.UISessionDays
import java.util.Calendar

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/12/2023.
 */
@Composable
fun SessionDayPickerSection(
    modifier: Modifier = Modifier,
    sessionDays: UISessionDays,
    selectedDay: Day? = null,
    selectedSession: Session? = null,
    onDayPicked: (Day) -> Unit,
    onSessionPicked: (Session) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        SectionWithText(
            header = stringResource(R.string.pick_session_day),
            text = stringResource(R.string.from_to, sessionDays.start, sessionDays.end),
        )
        DayPicker(days = sessionDays.days, selectedDay = selectedDay, onDayPicked = onDayPicked)
        AnimatedVisibility(visible = selectedDay?.sessions?.isNotEmpty() == true) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                val day = selectedDay!!
                SectionWithText(
                    header = stringResource(R.string.pick_session_hour),
                    text = stringResource(id = R.string.from_to, day.startHour, day.endHour),
                )
                SessionPicker(
                    sessions = day.sessions,
                    selectedSession = selectedSession,
                    onSessionPicked = onSessionPicked
                )
            }
        }
    }
}

@Composable
fun DayPicker(
    modifier: Modifier = Modifier,
    days: List<Day>,
    selectedDay: Day?,
    onDayPicked: (Day) -> Unit,
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(days, key = { it.dayOfMonth }) {
            val text = "${it.day.uppercase()}\n${it.dayOfMonth.toString().padStart(2, '0')}"
            PickerItem(
                text = text,
                modifier = Modifier
                    .width(30.dp)
                    .padding(
                    vertical = 4.dp,
                ),
                isSelected = selectedDay == it
            ) {
                onDayPicked(it)
            }
        }
    }
}

@Composable
fun SessionPicker(
    modifier: Modifier = Modifier,
    sessions: List<Session>,
    selectedSession: Session?,
    onSessionPicked: (Session) -> Unit,
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(sessions, key = { it.sessionId }) {
            val text = it.scheduledHour
            PickerItem(
                text = text,
                modifier = Modifier
                    .width(90.dp)
                    .padding(vertical = 8.dp),
                isSelected = selectedSession == it
            ) {
                onSessionPicked(it)
            }
        }
    }
}


@Composable
private fun PickerItem(
    modifier: Modifier = Modifier,
    text: String,
    isSelected: Boolean = false,
    onClick: () -> Unit,
) {
    val backgroundColor = if (isSelected) {
        Purple
    } else {
        DarkPurple
    }

    Box(
        modifier = Modifier
            .clip(shape = MaterialTheme.shapes.small)
            .background(backgroundColor)
            .clickable { onClick() }
            .padding(vertical = 8.dp, horizontal = 11.dp)
            .then(modifier),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
private fun PickerItemPrev() {
    SkillSyncTheme {
        ScreenColumn {
            val sessions = List(10) {
                Session(
                    sessionId = it.toString(),
                    name = "Session $it",
                    image = "",
                    skill = "Skill $it",
                    scheduledHour = "10:00 AM",
                    scheduledDate = "",
                    dateCalendar = Calendar.getInstance()
                )
            }
            val days = List(10) {
                Day(
                    day = "Mon",
                    dayOfMonth = it + 1,
                    startHour = "10:00 AM",
                    endHour = "11:00 AM",
                    sessions = sessions.shuffled().take(4)
                )
            }
            DayPicker(days = days, selectedDay = days.first()) {

            }

            SessionPicker(
                sessions = sessions,
                selectedSession = sessions.first(),
                onSessionPicked = {}
            )

            val sessionsDays = SessionDays(
                start = Calendar.getInstance(),
                end = Calendar.getInstance().apply { add(Calendar.DAY_OF_MONTH, 10) },
                days
            )
            val sessionDays = UISessionDays.from(sessionsDays)
            SessionDayPickerSection(
                sessionDays = sessionDays, onDayPicked = {},
                selectedDay = sessionDays.days.first(),
                selectedSession = sessionDays.days.first().sessions.random(),
                onSessionPicked = {

                },
            )

        }
    }
}
