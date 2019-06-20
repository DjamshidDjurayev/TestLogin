package com.example.djamshiddjuraev.testlogin

import com.example.djamshiddjuraev.testlogin.utils.Utils
import org.junit.Test

import org.junit.Assert.*

class UnitTest {
    @Test
    fun emailCheck_isCorrect() {
        assertTrue(Utils.isEmailValid("test@gmail.com"))
    }

    @Test
    fun emailCheck_inCorrect() {
        assertTrue(Utils.isEmailValid("test"))
    }

    @Test
    fun password_isCorrect() {
        assertTrue(Utils.isPasswordValid("Test123123"))
    }

    @Test
    fun passwordLength_inCorrect() {
        assertTrue(Utils.isPasswordValid("123"))
    }

    @Test
    fun passwordContainsAtLeastOneUpperCase_inCorrect() {
        assertTrue(Utils.isPasswordValid("test123123"))
    }

    @Test
    fun passwordContainsAtLeastOneLowerCase_inCorrect() {
        assertTrue(Utils.isPasswordValid("test123123"))
    }

    @Test
    fun passwordContainsAtLeastOneDigit_inCorrect() {
        assertTrue(Utils.isPasswordValid("Teststststs"))
    }
}
