package com.ss.skillsync.domain.usecase.session

import com.ss.skillsync.domain.repository.SessionRepository
import com.ss.skillsync.domain.util.StringUtil
import com.ss.skillsync.domain.util.getDayDisplayName
import com.ss.skillsync.domain.util.getDayOfMonth
import com.ss.skillsync.model.Day
import com.ss.skillsync.model.Mentor
import com.ss.skillsync.model.Session
import com.ss.skillsync.model.SessionDays
import com.ss.skillsync.model.SessionStatus
import java.util.Calendar
import javax.inject.Inject

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/11/2023.
 */
class GetMentorSessionDaysUseCase @Inject constructor(
    private val sessionRepository: SessionRepository,
) {
    suspend operator fun invoke(mentor: Mentor): Result<SessionDays> {
        val mentorSessions = sessionRepository.getMentorSessions(mentor).getOrElse {
            return Result.failure(it)
        }
        val sessions = mentorSessions.sessions.filter { it.status == SessionStatus.NOT_SELECTED }
        if (sessions.isEmpty()) {
            return Result.failure(Exception("No sessions available"))
        }

        val (from, to) = extractDaysRangeFromSessions(sessions)
        val days = extractDaysFromSessions(sessions, from, to)
        val sessionDays = SessionDays(
            start = from,
            end = to,
            days = days
        )
        return Result.success(sessionDays)
    }

    private fun extractDaysRangeFromSessions(sessions: List<Session>): Pair<Calendar, Calendar> {
        val from = sessions.minBy { it.dateCalendar.timeInMillis }
        val to = sessions.maxBy { it.dateCalendar.timeInMillis }
        return Pair(from.dateCalendar, to.dateCalendar)
    }

    private fun extractDaysFromSessions(
        sessions: List<Session>,
        from: Calendar,
        to: Calendar,
    ): List<Day> {
        val days = mutableListOf<Day>()
        val currentDay = from.clone() as Calendar
        while (currentDay <= to) {
            val day = getDayFromCalendar(currentDay, sessions)
            day?.let(days::add)
            currentDay.add(Calendar.DAY_OF_MONTH, 1)
        }
        return days
    }

    private fun getDayFromCalendar(calendar: Calendar, sessions: List<Session>): Day? {
        val dayString = calendar.getDayDisplayName()
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
        val daySessions = sessions.filter { it.dateCalendar.getDayOfMonth() == calendar.getDayOfMonth() }
            .map {
                it.copy(scheduledHour = StringUtil.reformatHourTo12System(it.scheduledHour))
            }
        var startHour = daySessions.minByOrNull { it.dateCalendar }?.scheduledHour ?: return null
        var endHour = daySessions.maxByOrNull { it.dateCalendar }?.scheduledHour ?: return null
        startHour = StringUtil.reformatHourTo12System(startHour)
        endHour = StringUtil.reformatHourTo12System(endHour)
        return Day(
            day = dayString,
            dayOfMonth = dayOfMonth,
            startHour = startHour,
            endHour = endHour,
            sessions = daySessions
        )
    }
}
