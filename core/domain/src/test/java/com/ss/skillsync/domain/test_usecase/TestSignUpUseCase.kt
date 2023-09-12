package com.ss.skillsync.domain.test_usecase

import com.ss.skillsync.domain.fake.FakeUserRepository
import com.ss.skillsync.domain.usecase.auth.SignupUseCase
import com.ss.skillsync.model.exception.EmailInvalidException
import com.ss.skillsync.model.exception.NameEmptyException
import com.ss.skillsync.model.exception.PasswordEmptyException
import com.ss.skillsync.model.exception.PasswordsDontMatchException
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/8/2023.
 */
class SignupUseCaseTest {

    private lateinit var signupUseCase: SignupUseCase
    private lateinit var userRepository: FakeUserRepository

    @Before
    fun setUp() {
        userRepository = FakeUserRepository()
        signupUseCase = SignupUseCase(userRepository)
    }

    @Test
    fun `signUp with valid input`() = runBlocking {
        val fullName = "John Doe"
        val email = "johndoe@example.com"
        val password = "password"
        val confirmPassword = "password"

        val result = signupUseCase(fullName, email, password, confirmPassword)

        assert(result.isSuccess)
    }

    @Test
    fun `signUp with invalid email`() = runBlocking {
        val fullName = "John Doe"
        val email = "invalid-email"
        val password = "password"
        val confirmPassword = "password"

        val result = signupUseCase(fullName, email, password, confirmPassword)

        assert(result.isFailure)
        assertTrue(result.exceptionOrNull() is EmailInvalidException)
    }

    @Test
    fun `signUp with blank name`() = runBlocking {
        val fullName = ""
        val email = "johndoe@example.com"
        val password = "password"
        val confirmPassword = "password"

        val result = signupUseCase(fullName, email, password, confirmPassword)

        assert(result.isFailure)
        assertTrue(result.exceptionOrNull() is NameEmptyException)
    }

    @Test
    fun `signUp with password mismatch`() = runBlocking {
        val fullName = "John Doe"
        val email = "johndoe@example.com"
        val password = "password"
        val confirmPassword = "differentpassword"

        val result = signupUseCase(fullName, email, password, confirmPassword)

        assert(result.isFailure)
        assertTrue(result.exceptionOrNull() is PasswordsDontMatchException)
    }

    @Test
    fun `signUp with blank password`() = runBlocking {
        val fullName = "John Doe"
        val email = "johndoe@example.com"
        val password = ""
        val confirmPassword = ""

        val result = signupUseCase(fullName, email, password, confirmPassword)

        assert(result.isFailure)
        assertTrue(result.exceptionOrNull() is PasswordEmptyException)
    }
}
