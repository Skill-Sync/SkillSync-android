package com.ss.skillsync.domain

import com.ss.skillsync.domain.util.ValidationUtil
import com.ss.skillsync.model.exception.EmailInvalidException
import com.ss.skillsync.model.exception.NameEmptyException
import com.ss.skillsync.model.exception.PasswordEmptyException
import com.ss.skillsync.model.exception.PasswordsDontMatchException
import org.junit.Assert.fail
import org.junit.Test

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/8/2023.
 */

class TestValidationUtil {

    @Test
    fun `validateName with non-blank name`() {
        val name = "John Doe"
        try {
            ValidationUtil.validateName(name)
        } catch (e: Exception) {
            fail("validateName should not throw an exception for a non-blank name")
        }
    }

    @Test(expected = NameEmptyException::class)
    fun `validateName with blank name`() {
        val name = ""
        ValidationUtil.validateName(name)
    }

    @Test
    fun `validateEmail with valid email`() {
        val email = "johndoe@example.com"
        try {
            ValidationUtil.validateEmail(email)
        } catch (e: Exception) {
            fail("validateEmail should not throw an exception for a valid email")
        }
    }

    @Test(expected = EmailInvalidException::class)
    fun `validateEmail with invalid email`() {
        val email = "invalid-email"
        ValidationUtil.validateEmail(email)
    }

    @Test
    fun `validatePassword with non-blank password`() {
        val password = "password"
        try {
            ValidationUtil.validatePassword(password)
        } catch (e: Exception) {
            fail("validatePassword should not throw an exception for a non-blank password")
        }
    }

    @Test(expected = PasswordEmptyException::class)
    fun `validatePassword with blank password`() {
        val password = ""
        ValidationUtil.validatePassword(password)
    }

    @Test
    fun `validatePasswords with matching passwords`() {
        val password = "password"
        val confirmPassword = "password"
        try {
            ValidationUtil.validatePasswords(password, confirmPassword)
        } catch (e: Exception) {
            fail("validatePasswords should not throw an exception when passwords match")
        }
    }

    @Test(expected = PasswordsDontMatchException::class)
    fun `validatePasswords with mismatched passwords`() {
        val password = "password"
        val confirmPassword = "differentpassword"
        ValidationUtil.validatePasswords(password, confirmPassword)
    }
}
