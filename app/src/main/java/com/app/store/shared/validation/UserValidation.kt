package com.app.store.shared.validation

import java.util.regex.Pattern

object UserValidation {

    private const val REGEX_EMAIL = "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})\$"

    fun isValidEmail(email: String): Boolean {
        val pattern = Pattern.compile(REGEX_EMAIL)
        val matcher = pattern.matcher(email)
        if (!matcher.matches()) { return false }
        return true
    }

    fun isValidPassword(password: String): Boolean {
        return password.length > 5
    }

    fun isCorrectName(name: String): Boolean {
        return name.length > 3
    }

}