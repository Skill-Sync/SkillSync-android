package com.ss.skillsync.domain.test_usecase

import com.ss.skillsync.domain.fake.FakeUserRepository
import com.ss.skillsync.domain.payload.SignInPayload
import com.ss.skillsync.domain.usecase.auth.IsUserActiveUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/8/2023.
 */
class IsUserActiveUseCaseTest {

    private lateinit var isUserActiveUseCase: IsUserActiveUseCase
    private lateinit var userRepository: FakeUserRepository

    @Before
    fun setUp() {
        userRepository = FakeUserRepository()
        isUserActiveUseCase = IsUserActiveUseCase(userRepository)
    }

    @Test
    fun `invoke when user is active`() = runBlocking {
        userRepository.signIn(SignInPayload("user@example.com", "password", "user"))

        val result = isUserActiveUseCase()

        assertTrue(result.isSuccess)
    }

    @Test
    fun `invoke when user is not active`() = runBlocking {
        userRepository.signOut()

        val result = isUserActiveUseCase()

        assertTrue(result.isFailure)
    }
}
