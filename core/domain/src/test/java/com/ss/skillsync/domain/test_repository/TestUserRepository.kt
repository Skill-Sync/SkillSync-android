package com.ss.skillsync.domain.test_repository

import com.ss.skillsync.domain.fake.FakeUserRepository
import com.ss.skillsync.domain.payload.SignInPayload
import com.ss.skillsync.domain.repository.UserRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/8/2023.
 */
class TestUserRepository {

    private lateinit var userRepository: UserRepository

    @Before
    fun setUp() {
        userRepository = FakeUserRepository()
    }

    @Test
    fun testSignIn() = runBlocking {
        val signInPayload = SignInPayload("testuser", "testpassword", "user")
        val result = userRepository.signIn(signInPayload)
        assertTrue(result.isSuccess)
        val user = result.getOrNull()!!
        assertNotNull(user)
        assertEquals("FakeUser", user.name)
        assertEquals("fakeuser@example.com", user.email)
    }

    @Test
    fun testSignOut() = runBlocking {
        userRepository.signOut()
        val result = userRepository.getActiveUser()
        assertTrue(result.isFailure)
        assertEquals("User not found", result.exceptionOrNull()!!.message)
    }

    @Test
    fun testIsFirstOpen() = runBlocking {
        assertTrue(userRepository.isFirstOpen())
        assertFalse(userRepository.isFirstOpen())
    }
}
