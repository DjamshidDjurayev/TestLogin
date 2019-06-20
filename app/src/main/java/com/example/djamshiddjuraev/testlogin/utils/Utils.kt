package com.example.djamshiddjuraev.testlogin.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.util.regex.Pattern

class Utils {
  companion object {
    fun isEmailValid(email: String): Boolean {
      val pattern = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
          "\\@" +
          "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
          "(" +
          "\\." +
          "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
          ")+"
      return Pattern.compile(pattern).matcher(email).matches()
    }

    fun isPasswordValid(password: String): Boolean {
      val pattern = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,30})"
      return Pattern.compile(pattern).matcher(password).matches()
    }

    fun hideKeyboard(view: View?) {
      if (view == null) return

      try {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (!imm.isActive) {
          return
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
      } catch (e: Exception) {
        e.printStackTrace()
      }

    }
  }
}