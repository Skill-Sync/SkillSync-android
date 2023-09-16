package com.ss.skillsync.domain.test_usecase

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/16/2023.
 */
import com.ss.skillsync.domain.fake.FakeSessionRepository
import com.ss.skillsync.domain.usecase.session.GetMentorSessionDaysUseCase
import com.ss.skillsync.model.Mentor
import com.ss.skillsync.model.Session
import com.ss.skillsync.model.SessionDays
import com.ss.skillsync.model.SessionStatus
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.util.Calendar

class GetMentorSessionDaysUseCaseTest {

    private lateinit var useCase: GetMentorSessionDaysUseCase
    private lateinit var fakeRepository: FakeSessionRepository

    @Before
    fun setup() {
        fakeRepository = FakeSessionRepository()
        useCase = GetMentorSessionDaysUseCase(fakeRepository)
    }

    @Test
    fun `GetMentorSessionDaysUseCase returns session days`() {
        val mentor = Mentor()
        val sessions = listOf(
            Session(dateCalendar = Calendar.getInstance(), status = SessionStatus.NOT_SELECTED)
        )

        fakeRepository.addMentorSessions(mentor, sessions)

        val result = runBlocking { useCase(mentor) }

        assert(result.isSuccess)
        val sessionDays = result.getOrNull() as SessionDays
        assertEquals(sessionDays.days.size, 1)

    }

    @Test
    fun `GetMentorSessionDaysUseCase returns failure when no sessions available`() {
        val mentor = Mentor()

        fakeRepository.addMentorSessions(mentor, emptyList())

        val result = runBlocking { useCase(mentor) }

        assert(result.isFailure)
    }
}