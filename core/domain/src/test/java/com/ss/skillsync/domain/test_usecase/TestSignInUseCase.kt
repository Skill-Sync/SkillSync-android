package com.ss.skillsync.domain.test_usecase

import com.ss.skillsync.domain.fake.FakeUserRepository
import com.ss.skillsync.domain.usecase.auth.SignInUseCase
import com.ss.skillsync.model.exception.EmailInvalidException
import com.ss.skillsync.model.exception.PasswordEmptyException
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/8/2023.
 */
class SignInUseCaseTest {

    private lateinit var signInUseCase: SignInUseCase
    private lateinit var userRepository: FakeUserRepository

    @Before
    fun setUp() {
        userRepository = FakeUserRepository()
        signInUseCase = SignInUseCase(userRepository)
    }

    @Test
    fun `signIn with valid email and password`() = runBlocking {
        val email = "fakeuser@example.com"
        val password = "password"

        val result = signInUseCase(email, password, "user")

        assert(result.isSuccess)
        val user = result.getOrNull()
        assertNotNull(user)
        assertEquals("FakeUser", user?.name)
        assertEquals(email, user?.email)
    }

    @Test
    fun `signIn with invalid email`() = runBlocking {
        val email = "invalid-email"
        val password = "password"

        val result = signInUseCase(email, password, "user")

        assert(result.isFailure)
        assertTrue(result.exceptionOrNull() is EmailInvalidException)
    }

    @Test
    fun `signIn with blank password`() = runBlocking {
        val email = "user@example.com"
        val password = ""

        val result = signInUseCase(email, password, "user")

        assert(result.isFailure)
        assertTrue(result.exceptionOrNull() is PasswordEmptyException)
    }

    @Test
    fun `signIn with valid email and blank password`() = runBlocking {
        val email = "user@example.com"
        val password = " "

        val result = signInUseCase(email, password, "user")
        assert(result.isFailure)
    }
}
