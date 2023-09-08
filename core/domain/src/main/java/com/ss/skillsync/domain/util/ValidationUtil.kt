package com.ss.skillsync.domain.util

import com.ss.skillsync.model.exception.EmailInvalidException
import com.ss.skillsync.model.exception.NameEmptyException
import com.ss.skillsync.model.exception.PasswordEmptyException
import com.ss.skillsync.model.exception.PasswordsDontMatchException

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/8/2023.
 */
object ValidationUtil {

    fun validateName(name: String) {
        if (name.isBlank()) {
            throw NameEmptyException()
        }
    }
    fun validateEmail(email: String) {
        if (email.isBlank()) {
            throw EmailInvalidException()
        }
        val regex = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$")
        if (!regex.matches(email)) {
            throw EmailInvalidException()
        }
    }

    fun validatePassword(password: String) {
        if (password.isBlank()) {
            throw PasswordEmptyException()
        }
    }
    fun validatePasswords(password: String, confirmPassword: String) {
        validatePassword(password)
        validatePassword(confirmPassword)

        if (password != confirmPassword) {
            throw PasswordsDontMatchException("Passwords don't match")
        }
    }
}
